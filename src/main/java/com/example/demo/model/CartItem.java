package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "cart_items")
public class CartItem implements Serializable {


    @EmbeddedId
    private UserProductPK pk;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Long quantity;


    @Column(name = "size_product")
    private String sizeProduct;
    


    public CartItem() {
    }

    public CartItem(User user, Product product, Long quantity, String sizeProduct) {
        this.pk = new UserProductPK(user.getId(), product.getProduct_id());
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.sizeProduct = sizeProduct;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getSizeProduct() {
        return this.sizeProduct;
    }

    public void setSizeProduct(String sizeProduct) {
        this.sizeProduct = sizeProduct;
    }


    public UserProductPK getPk() {
        return this.pk;
    }

    public void setPk(UserProductPK pk) {
        this.pk = pk;
    }
   

    @Override
    public String toString() {
        return "{" +
            " pk='" + getPk() + "'" +
            ", user='" + getUser() + "'" +
            ", product='" + getProduct() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", sizeProduct='" + getSizeProduct() + "'" +
            "}";
    }

}