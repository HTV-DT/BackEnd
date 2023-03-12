
package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CartItem;
import com.example.demo.repository.ICartItemRepository;
import com.example.demo.service.CartItemService;




@Service
public class CartItemServiceImpl implements CartItemService {
    
    @Autowired
   ICartItemRepository cartItemRepository;


   @Override
   public List<CartItem> findAllCartItems() {
       List<CartItem> cartItems = cartItemRepository.findAll();
       return cartItems;
   }

    @Override
    public CartItem save(CartItem cartItem){
      
        return cartItemRepository.save(cartItem);
        //return cartItemRepository.saveCartItem(cartItem.getQuantity(),cartItem.getSizeProduct(),cartItem.getProduct().getProduct_id(),cartItem.getUser().getId());
    }

    @Override
    public boolean delete(Long idU,Long idP){
     
      try {
       // cartItemRepository.dltCartItem(idU,idP);
        return true;
      } catch (Exception e) {
        return false;
      }
    }

  
       
   
}