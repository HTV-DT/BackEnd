package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "productSizes")
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long size_id;
    @NotBlank
    @Size(max = 20)
    private String size_name;


    public ProductSize() {
    }

    public ProductSize(Long size_id, String size_name) {
        this.size_id = size_id;
        this.size_name = size_name;
    }


    public Long getSize_id() {
        return this.size_id;
    }

    public void setSize_id(Long size_id) {
        this.size_id = size_id;
    }

    public String getSize_name() {
        return this.size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
    }

    
}
