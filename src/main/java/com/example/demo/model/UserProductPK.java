package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class UserProductPK implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;


    public UserProductPK() {
    }

    
    public UserProductPK(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

}