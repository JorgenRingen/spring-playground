package org.springplayground.samples.demoapp.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Simple plain JUnit-test example without spring and mockito.
 */
class OrderTest {

    @Test
    void addOrderline() {
        Order order = new Order();
        assertThat(order.getOrderlines()).hasSize(0);

        order.addOrderline(new Orderline());
        assertThat(order.getOrderlines()).hasSize(1);
    }

    @Test
    void removeOrderline() {
        long orderlineId = 42L;

        Order order = new Order();

        Orderline orderline = new Orderline();
        orderline.setId(orderlineId);

        order.addOrderline(orderline);
        assertThat(order.getOrderlines()).hasSize(1);

        order.removeOrderline(orderlineId);
        assertThat(order.getOrderlines()).hasSize(0);
    }
}