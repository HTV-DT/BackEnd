package com.example.demo.dto.request;

public class CartItemForm {
    private Long userId;
    private Long productId;
    private Long quantity;
    private String sizeProduct;


    public CartItemForm() {
    }



    public CartItemForm(Long userId, Long productId, Long quantity, String sizeProduct) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.sizeProduct = sizeProduct;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }


    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


    public String getSizeProduct() {
        return this.sizeProduct;
    }

    public void setSizeProduct(String sizeProduct) {
        this.sizeProduct = sizeProduct;
    }


}
