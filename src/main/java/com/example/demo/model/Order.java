package com.example.demo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;


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


    @OneToMany(mappedBy="order") // chú ý biến Category này được khai báo trong Class Product bên dưới. Chúng phải giống y chang nhau cái tên
    Set<OrderItem> orderItems;
    
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

    public Set<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public void addProduct(Product product, Long quantity ) {
        OrderItem orderItem = new OrderItem(this,product , quantity);
        orderItems.add(orderItem);
    }
 
    public void removeProduct(Product product) {
        for (Iterator<OrderItem> iterator = orderItems.iterator(); iterator.hasNext();) {
            OrderItem orderItem= iterator.next();
 
            if (orderItem.getOrder().equals(this) && orderItem.getProduct().equals(product)) {
                iterator.remove();
                orderItem.setOrder(null);
                orderItem.setProduct(null);
            }
        }
    }


    // public Set<Product> getProducts() {
    //     return this.products;
    // }

    // public void setProducts(Set<Product> products) {
    //     this.products = products;
    // }


}