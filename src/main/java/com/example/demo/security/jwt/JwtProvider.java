package com.example.demo.security.jwt;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.security.userprincal.UserPrinciple;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {//Tạo Token
    private static final Logger  logger =LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "vi.huynhthanh@code.vn";
    private int  jwtExpriration=86400;
    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple =(UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+jwtExpriration*1000))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }       
    
    //Các lổi có thể xuất hiện
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e){
            logger.error("Ivalid JWT sinature->Message: {}"+e );
        } catch (MalformedJwtException e){
            logger.error("The token invalid format ->Message:{}"+e );
        } catch (UnsupportedJwtException e){
            logger.error("Unsupported JWT token ->Message:{}"+e );
        } catch (ExpiredJwtException e){
            logger.error("Exprired JWT Token ->Message:{}"+e );
        } catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty ->Message:{}"+e );
        }
        return false;
    }    

    //Lấy tên người dùng
    public String getUserNameFromToken(String token){
        String userName =Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
    
}
