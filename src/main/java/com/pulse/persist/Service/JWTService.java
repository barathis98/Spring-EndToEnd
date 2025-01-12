package com.pulse.persist.Service;


import com.pulse.persist.Model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;


@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;


    public String generateJWTToken(User user){
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", user.getUserId());
        claims.put("email", user.getEmail());

        return Jwts
                .builder()
                .claims()
                .add(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .and()
                .signWith(generateKey())
                .issuer("Pulse")
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .compact();
    }

    private SecretKey generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUserNameFromJwtToken(String token){
        return getClaimsFromToken(token).get("email", String.class);
    }



    public boolean isValidToken(String token, UserDetails userDetails) {

        try {
            String email = getUserNameFromJwtToken(token);
            return (userDetails.getUsername().equals(email)) && !isTokenExpired(token);

        } catch (ExpiredJwtException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT is expired");
        } catch (UnsupportedJwtException | MalformedJwtException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT was not correctly constructed");
        } catch (JwtException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unexpected JWT error");
        }
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    public Date getExpiration(String token){
        return getClaimsFromToken(token).getExpiration();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
