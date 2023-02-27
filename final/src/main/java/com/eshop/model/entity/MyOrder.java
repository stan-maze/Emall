package com.eshop.model.entity;

import java.util.Date;
import java.util.List;

public class MyOrder {
    private int id;
    private User user;
    private List<CartItem> items;
    private double total;
    private Date createdDate;

    @Override
    public String toString() {
        return "MyOrder{" +
                "id=" + id +
                ", user=" + user +
                ", items=" + items +
                ", total=" + total +
                ", createdDate=" + createdDate +
                '}';
    }

    public MyOrder(int id, User user, List<CartItem> items, double total, Date createdDate) {
        this.id = id;
        this.user = user;
        this.items = items;
        this.total = total;
        this.createdDate = createdDate;
    }

    public MyOrder() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}