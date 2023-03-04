package com.example.demo.model;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "cart_items")
public class Cart_item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;
    @NotBlank
    @Size(max = 255)
    private String category_name;


}