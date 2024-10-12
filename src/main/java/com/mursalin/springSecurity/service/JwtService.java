package com.mursalin.springSecurity.service;

import com.mursalin.springSecurity.model.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    public String getJwtToken(Users user) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*10))
                .and()
                .signWith(key())
                .compact();
    }

    private Key key() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();
            String secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
            byte[] keyByte = Decoders.BASE64.decode(secretKey);
            return Keys.hmacShaKeyFor(keyByte);
            //return Keys.hmacShaKeyFor(Decoders.BASE64.decode(Base64.getEncoder().encodeToString(keyGenerator.generateKey().getEncoded())));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
