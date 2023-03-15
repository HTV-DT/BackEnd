package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    @NotBlank
    @Size(max = 255)
    private String product_name;
    @NotBlank
    @Column(columnDefinition="TEXT")
    private String description;
    @NotBlank
    @Size(max = 100)
    private Double price;
    @NotBlank
    @Lob
    private String image_url;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_size_junction",
    joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "size_id"))
    Set<ProductSize> productSizes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false) //ctegory_id chính là trường khoá phụ trong table Product liên kết với khóa chính trong table Category
    private Category category;
    
    @JsonManagedReference
    @OneToMany(mappedBy="product") 
    Set<CartItem> cartItems;

 
    @OneToMany(fetch = FetchType.LAZY,mappedBy="product",cascade = CascadeType.ALL) // chú ý biến Category này được khai báo trong Class Product bên dưới. Chúng phải giống y chang nhau cái tên
    Set<OrderItem> orderItems= new HashSet<>();

    public Product() {
    }


    public Product( String product_name, String description, Double price, String image_url, Set<ProductSize> productSizes, Category category) {
      
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.image_url = image_url;
        this.productSizes = productSizes;
        this.category = category;
    }
   


    public Long getProduct_id() {
        return this.product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
   

    public String getProduct_name() {
        return this.product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    public Set<ProductSize> getProductSizes() {
        return this.productSizes;
    }

    public void setProductSizes(Set<ProductSize> productSizes) {
        this.productSizes = productSizes;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
    public Set<CartItem> getCartItems() {
        return this.cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    
    @Override
    public String toString() {
        return "{" +
            " product_id='" + getProduct_id() + "'" +
            ", product_name='" + getProduct_name() + "'" +
            ", description='" + getDescription() + "'" +
            ", price='" + getPrice() + "'" +
            ", image_url='" + getImage_url() + "'" +
            ", productSizes='" + getProductSizes() +
            "}";
    }





}
