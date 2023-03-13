package com.example.demo.controller;

import com.example.demo.dto.request.CartItemForm;
import com.example.demo.dto.request.SignInForm;
import com.example.demo.dto.request.SignUpForm;
import com.example.demo.dto.response.JwtResponse;
import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.model.Role;
import com.example.demo.model.RoleName;
import com.example.demo.model.User;
import com.example.demo.model.UserProductPK;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.userprincal.UserPrinciple;
import com.example.demo.service.impl.CartItemServiceImpl;
import com.example.demo.service.impl.ProductServiceImpl;
import com.example.demo.service.impl.RoleServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;

import lombok.var;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequestMapping("/api/auth")
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    CartItemServiceImpl cartItemService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (userService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponMessage("The username existed"), HttpStatus.OK);
        }
        if (userService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponMessage("The email existed"), HttpStatus.OK);
        }
        User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(),
                passwordEncoder.encode(signUpForm.getPassword()),signUpForm.getAddress());
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "ADMIN":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(
                            () -> new RuntimeException("Role not found"));
                    roles.add(adminRole);
                    break;
                case "PM":
                    Role pmRole = roleService.findByName(RoleName.PM)
                            .orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER)
                            .orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponMessage("yes"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        //Xác thực
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getAuthorities()));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> listRegisteredUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/products") // List Products
    public ResponseEntity<List<Product>> listRegisteredProduct() {
        List<Product> products = productService.findAllProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{id}") // List Products
    public ResponseEntity<Optional<Product>> productId(@PathVariable("id") long id) {
        Optional<Product> product =productService.findByProduct_id(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/add/product")
    public ResponseEntity<?> register(@Valid @RequestBody CartItemForm cartItemForm, BindingResult bindingResult)  {
         Optional<User> user =userService.findById(cartItemForm.getUserId());
         Optional<Product> product =productService.findByProduct_id(cartItemForm.getProductId());
         User userId=user.get();
         Product productId=product.get();
         userId.addProduct(productId,cartItemForm.getQuantity(),cartItemForm.getSizeProduct());
        userService.save(userId);
        return new ResponseEntity<>(new ResponMessage("yes"), HttpStatus.OK);
    }

    @GetMapping("/CartItems") // List Products
    public ResponseEntity<List<CartItem>> listRegisteredCartItem() {
        List<CartItem> cartItems = cartItemService.findAllCartItems();
        return ResponseEntity.ok(cartItems);
    }
    
//     @DeleteMapping("/deleteProduct")
//     public  ResponseEntity<String> deleteNhanVien(@Valid @RequestBody CartItemForm cartItemForm) {
//         Optional<User> user =userService.findById(cartItemForm.getUserId());
//         Optional<Product> product =productService.findByProduct_id(cartItemForm.getProductId());
//         User userId=user.get();
//         Product productId=product.get();
//         userId.removeProduct(productId);
//         userService.save(userId);
//         return new ResponseEntity<String>(" deleted successfully!.", HttpStatus.OK);
//     }

}
