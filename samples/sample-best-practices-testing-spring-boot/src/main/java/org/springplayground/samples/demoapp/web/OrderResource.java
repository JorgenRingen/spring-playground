package org.springplayground.samples.demoapp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springplayground.samples.demoapp.domain.Order;
import org.springplayground.samples.demoapp.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderResource {

    private final OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> findAll() {
        return orderService.findOrders();
    }

    @GetMapping("{orderId}")
    public ResponseEntity findById(@PathVariable long orderId) {
        Optional<Order> optionalOrder = orderService.findOrderById(orderId);
        if (optionalOrder.isPresent()) {
            return ResponseEntity.ok(optionalOrder.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Order post(Order order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity delete(@PathVariable long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{orderId}/submit")
    public Order submit(@PathVariable long orderId) {
        return orderService.submitOrder(orderId);
    }
}
