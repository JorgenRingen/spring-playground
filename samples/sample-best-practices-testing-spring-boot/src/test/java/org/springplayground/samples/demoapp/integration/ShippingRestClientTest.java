package org.springplayground.samples.demoapp.integration;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springplayground.samples.demoapp.domain.Order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Rest-client test example.
 * <p>
 * Uses {@link MockRestServiceServer} to create an http-server for the test.
 */
@ExtendWith(SpringExtension.class)
@RestClientTest(
        value = ShippingRestClient.class,
        properties = "integration.shipping.url=http://does-not-exist:8080")
class ShippingRestClientTest {

    @Autowired
    private ShippingRestClient shippingRestClient;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Test
    void testShipWhenReturnStatusIsOk() {
        long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);

        String expectedShippingId = UUID.randomUUID().toString();

        this.mockRestServiceServer
                .expect(requestTo("http://does-not-exist:8080"))
                .andExpect(method(POST))
                .andExpect(content().string(String.valueOf(orderId)))
                .andRespond(withSuccess(expectedShippingId, MediaType.APPLICATION_JSON));

        String shippingId = shippingRestClient.ship(order);

        assertThat(shippingId).isEqualTo(expectedShippingId);
    }

    @Test
    void testShipWhenReturnStatusIs500() {
        long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);

        this.mockRestServiceServer
                .expect(requestTo("http://does-not-exist:8080"))
                .andExpect(method(POST))
                .andExpect(content().string(String.valueOf(orderId)))
                .andRespond(withServerError());

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> shippingRestClient.ship(order))
                .withMessageContaining("Shipping failed")
                .withMessageContaining("Internal Server Error");
    }

}