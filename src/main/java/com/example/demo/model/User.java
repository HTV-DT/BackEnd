package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
    @NaturalId
    @NotBlank
    @Size(max = 100)
    @Email
    private String email;
    @JsonIgnore
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
    @NotBlank
    private String address;

    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    // @ManyToMany(fetch = FetchType.EAGER)
    // @JoinTable(name = "cart_items",
    // joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    // Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy="user") // chú ý biến Category này được khai báo trong Class Product bên dưới. Chúng phải giống y chang nhau cái tên
    private Set<Order> orders;

    
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy="user",cascade = CascadeType.ALL) // chú ý biến Category này được khai báo trong Class Product bên dưới. Chúng phải giống y chang nhau cái tên
    Set<CartItem> cartItems= new HashSet<>();

    

    public User() {
    }

    public User( String name, String username, String email, String password,String address,  Set<Role> roles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.roles = roles;
    }

    public User(  @NotBlank @Size(min = 3, max = 50)String name,
                  @NotBlank @Size(min = 3, max = 50)String username,
                  @NotBlank @Size(max = 50) @Email String email,
                  @NotBlank @Size(min = 6, max = 100)String encode,
                  @NotBlank String address
                  ) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = encode;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    
    public Set<CartItem> getCartItems() {
        return this.cartItems;
        
    }
    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addProduct(Product product, Long quantity, String sizeProduct ) {
        CartItem cartItem = new CartItem(this, product,quantity,sizeProduct);
        cartItems.add(cartItem);
    }
 
    public void removeProduct(Product product) {
        for (Iterator<CartItem> iterator = cartItems.iterator(); iterator.hasNext();) {
            CartItem cartItem = iterator.next();
 
            if (cartItem.getUser().equals(this) && cartItem.getProduct().equals(product)) {
                iterator.remove();
                cartItem.setUser(null);
                cartItem.setProduct(null);
            }
        }
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", address='" + getAddress() + "'" +
            ", roles='" + getRoles() + "'" +
            ", orders='" + getOrders() +
            "}";
    }

}
