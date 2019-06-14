package org.springplayground.samples.demoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springplayground.samples.demoapp.domain.Order;
import org.springplayground.samples.demoapp.domain.Orderstatus;
import org.springplayground.samples.demoapp.repository.OrderRespository;

import org.springplayground.samples.demoapp.integration.ShippingRestClient;

@Service
@Transactional
public class OrderService {

    private final OrderRespository orderRespository;
    private final ShippingRestClient shippingRestClient;

    @Autowired
    public OrderService(OrderRespository orderRespository, ShippingRestClient shippingRestClient) {
        this.orderRespository = orderRespository;
        this.shippingRestClient = shippingRestClient;
    }

    public List<Order> findOrders() {
        return orderRespository.findAll();
    }

    public Optional<Order> findOrderById(long orderId) {
        return orderRespository.findById(orderId);
    }

    public Order createOrder(Order order) {
        order.setOrderstatus(Orderstatus.CREATED);
        return orderRespository.save(order);
    }

    public Order submitOrder(long orderId) {
        Optional<Order> optionalOrder = orderRespository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new IllegalArgumentException("Order with id=" + orderId + " not found");
        }

        Order order = optionalOrder.get();
        order.setOrderstatus(Orderstatus.COMPLETED);

        String shippingId = shippingRestClient.ship(order);
        order.setShippingId(shippingId);

        return order;
    }

    public void deleteOrder(long orderId) {
        orderRespository.findById(orderId).ifPresent(orderRespository::delete);
    }
}
