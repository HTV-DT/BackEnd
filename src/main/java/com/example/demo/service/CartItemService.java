package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.CartItem;
import com.example.demo.model.UserProductPK;

public interface CartItemService {
   
    List<CartItem> findAllCartItems();
     CartItem save(CartItem cartItem);
     boolean delete(Long idU,Long idP);
     
}
