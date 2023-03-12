package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ICategoryRepository;
import com.example.demo.service.CategoryService;



@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
   ICategoryRepository categoryRepository;

   
}