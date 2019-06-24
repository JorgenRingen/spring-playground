package org.springplayground.samples.demoapp.web;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springplayground.samples.demoapp.domain.Order;
import org.springplayground.samples.demoapp.service.OrderService;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Simple JUnit test with {@link MockMvc} (only demonstrates a subset of the functionality)
 * <p>
 * This only starts the web-layer of spring without starting the server. Other layers are mocked.
 * <p>
 * Should be used to test controller-handling. Can become redundant to integration-tests which tests
 * the whole application on a started server.
 *
 * @see OrderResourceIT
 */
@WebMvcTest(controllers = OrderResource.class)
class OrderResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void findAll() throws Exception {
        Order order = new Order();
        order.setId(42L);

        when(orderService.findOrders()).thenReturn(Collections.singletonList(order));

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("42")));
    }

    @Test
    void deleteOrder() throws Exception {
        mockMvc.perform(delete("/orders/42"))
                .andExpect(status().isNoContent());

        verify(orderService).deleteOrder(42);
    }
}