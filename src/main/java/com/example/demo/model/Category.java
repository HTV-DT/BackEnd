package com.example.demo.model;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;
    @NotBlank
    @Size(max = 255)
    private String category_name;

    @OneToMany(mappedBy="category") // chú ý biến Category này được khai báo trong Class Product bên dưới. Chúng phải giống y chang nhau cái tên
    private Set<Product> products;

    public Category() {
    }

    public Category(Long category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public Long getCategory_id() {
        return this.category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return this.category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

}