package org.springplayground.samples.demoapp.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springplayground.samples.demoapp.config.DatabaseConfig;
import org.springplayground.samples.demoapp.domain.Order;
import org.springplayground.samples.demoapp.domain.Orderline;
import org.springplayground.samples.demoapp.domain.Orderstatus;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Repository-test example (only demonstrates a subset of the functionality)
 * <p>
 * Uses {@link DataJpaTest} to set up a H2 in-memory database,
 * imports the {@link DatabaseConfig} and injects the {@link OrderRespository} in the test.
 * <p>
 * NB: You can also inject the {@link org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager}
 * in the test to do more advanced operations.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DatabaseConfig.class)
class OrderRespositoryTest {

    @Autowired
    private OrderRespository orderRespository;

    @Test
    void testSaveOrder() {
        // test-data
        Order order = new Order();
        order.setOrderstatus(Orderstatus.COMPLETED);

        Orderline orderline = new Orderline();
        orderline.setProductId("42");
        orderline.setQuantity(43);
        order.addOrderline(orderline);

        // save
        Order savedOrder = orderRespository.save(order);

        // verify
        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.getOrderstatus()).isEqualTo(order.getOrderstatus());
        assertThat(savedOrder.getOrderlines()).hasSize(order.getOrderlines().size());

        Orderline savedOrderline = savedOrder.getOrderlines().get(0);
        assertThat(savedOrderline.getId()).isNotNull();
        assertThat(savedOrderline.getProductId()).isEqualTo(orderline.getProductId());
        assertThat(savedOrderline.getQuantity()).isEqualTo(orderline.getQuantity());
    }
}