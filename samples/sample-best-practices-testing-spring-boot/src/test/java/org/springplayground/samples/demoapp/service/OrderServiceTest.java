package org.springplayground.samples.demoapp.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springplayground.samples.demoapp.domain.Order;
import org.springplayground.samples.demoapp.domain.Orderstatus;
import org.springplayground.samples.demoapp.repository.OrderRespository;

import org.springplayground.samples.demoapp.integration.ShippingRestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Simple JUnit + Mockito test.
 * Only tests the service layer - all other layers are mocked (integration and repository).
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRespository orderRespository;

    @Mock
    private ShippingRestClient shippingRestClient;

    @InjectMocks
    private OrderService orderService;

    @Test
    void findOrders() {
        List<Order> expectedOrders = Collections.emptyList();
        when(orderRespository.findAll()).thenReturn(expectedOrders);

        List<Order> orders = orderService.findOrders();
        assertThat(orders).isEqualTo(expectedOrders);
    }

    @Test
    void findOrderById() {
        Optional<Order> expectedOptionalOrder = Optional.of(new Order());
        when(orderRespository.findById(42L)).thenReturn(expectedOptionalOrder);

        Optional<Order> optionalOrder = orderService.findOrderById(42);
        assertThat(optionalOrder).isEqualTo(expectedOptionalOrder);
    }

    @Test
    void createOrder() {
        Order order = new Order();
        when(orderRespository.save(order)).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);
        assertThat(createdOrder).isEqualTo(order);
        assertThat(createdOrder.getOrderstatus()).isEqualTo(Orderstatus.CREATED);
    }

    @Test
    void submitOrder() {
        String shippingId = "-1";

        Order order = new Order();
        order.setId(42L);

        when(orderRespository.findById(order.getId())).thenReturn(Optional.of(order));
        when(shippingRestClient.ship(order)).thenReturn(shippingId);

        Order submittedOrder = orderService.submitOrder(order.getId());

        assertThat(submittedOrder.getOrderstatus()).isEqualTo(Orderstatus.COMPLETED);
        assertThat(submittedOrder.getShippingId()).isEqualTo(shippingId);

        verify(shippingRestClient).ship(order);
    }

    @Test
    void deleteOrder() {
        Order order = new Order();
        order.setId(42);
        when(orderRespository.findById(order.getId())).thenReturn(Optional.of(order));

        orderService.deleteOrder(order.getId());

        verify(orderRespository).delete(order);
    }
}