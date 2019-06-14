package org.springplayground.samples.demoapp.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Orderline> orderlines = new ArrayList<>();

    private Orderstatus orderstatus;

    private String shippingId;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Orderline> getOrderlines() {
        return new ArrayList<>(this.orderlines);
    }

    public void addOrderline(Orderline orderline) {
        this.orderlines.add(orderline);
    }

    public void removeOrderline(long orderlineId) {
        Optional<Orderline> optionalOrderline = this.orderlines.stream()
                .filter(orderline -> orderline.getId().equals(orderlineId))
                .findAny();
        optionalOrderline.ifPresent(orderline -> this.orderlines.remove(orderline));
    }

    public Orderstatus getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Orderstatus orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getShippingId() {
        return shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }
}