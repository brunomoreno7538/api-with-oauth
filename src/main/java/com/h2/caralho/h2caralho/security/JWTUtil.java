package com.h2.caralho.h2caralho.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

public class JWTUtil {
    @Value("${jwt.secret}")
    private String key;
    @Value("${jwt.expiration}")
    private String expiracao;

    private String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiracao))
                .signWith(SignatureAlgorithm.HS512, key.getBytes())
                .compact();
    }
}
