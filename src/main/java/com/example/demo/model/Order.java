package com.example.demo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    @NotBlank
    private Date order_date;
    @NotBlank
    @Size(min=0 ,max=10)
    private Double total_price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_items",
    joinColumns = @JoinColumn(name = "order_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    Set<Product> products = new HashSet<>();

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="user_id", nullable=false) //ctegory_id chính là trường khoá phụ trong table Product liên kết với khóa chính trong table Category
    private User user;

    public Order() {
    }

    public Order(Long order_id, Date order_date, Double total_price) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.total_price = total_price;
    }

    public Long getOrder_id() {
        return this.order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_date() {
        return this.order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Double getTotal_price() {
        return this.total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }
    

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


}