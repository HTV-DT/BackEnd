
package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.service.OrderService;



@Service
public class OderServiceImpl implements OrderService {
    
    @Autowired
    IOrderRepository orderRepository;

    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }
}