package org.springplayground.samples.demoapp.web;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springplayground.samples.demoapp.domain.Order;
import org.springplayground.samples.demoapp.domain.Orderline;
import org.springplayground.samples.demoapp.domain.Orderstatus;

import org.springplayground.samples.demoapp.integration.ShippingRestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Integration-test example (only demonstrates a subset of the functionality).
 * <p>
 * Starts the application on an embedded server. This is the most realistic, but also most expensive, type of tests.
 * Performs actual HTTP-calls to the application.
 * <p>
 * These types of tests should focused on user-journey tests and use-cases.
 * <p>
 * {@link org.springframework.boot.test.mock.mockito.MockBean} and
 * {@link org.springframework.boot.test.mock.mockito.SpyBean} can
 * be used for mocking.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderResourceIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ShippingRestClient shippingRestClient;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    @Test
    void testSubmitOrder() {
        Order order = createOrder();

        // setup shipping mock
        String shippingId = UUID.randomUUID().toString();
        when(shippingRestClient.ship(orderArgumentCaptor.capture())).thenReturn(shippingId);

        // submit order
        ResponseEntity<Order> submitOrderResponse = restTemplate.postForEntity("/orders/" + order.getId() + "/submit", null, Order.class);
        assertThat(submitOrderResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        // verify shipment call
        assertThat(orderArgumentCaptor.getValue().getId()).isEqualTo(order.getId());
        verify(shippingRestClient).ship(any(Order.class));
        verifyNoMoreInteractions(shippingRestClient);

        // retrieve submitted order and verify
        ResponseEntity<Order> responseRetrievedOrder = restTemplate.getForEntity("/orders/" + order.getId(), Order.class);
        assertThat(responseRetrievedOrder.getStatusCode()).isEqualTo(HttpStatus.OK);
        Order submittedOrder = responseRetrievedOrder.getBody();
        assertThat(submittedOrder).isNotNull();
        assertThat(submittedOrder.getOrderstatus()).isEqualTo(Orderstatus.COMPLETED);
        assertThat(submittedOrder.getShippingId()).isEqualTo(shippingId);
    }

    private Order createOrder() {
        Order order = new Order();
        Orderline orderline = new Orderline();
        orderline.setQuantity(42);
        orderline.setProductId("43");
        order.addOrderline(orderline);

        // create order
        ResponseEntity<Order> createOrderResponse = restTemplate.postForEntity("/orders", order, Order.class);
        assertThat(createOrderResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Order createdOrder = createOrderResponse.getBody();
        assertThat(createdOrder.getId()).isNotNull();
        assertThat(createdOrder.getOrderstatus()).isEqualTo(Orderstatus.CREATED);

        return createdOrder;
    }
}