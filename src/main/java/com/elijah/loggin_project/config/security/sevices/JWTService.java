package com.elijah.loggin_project.config.security.sevices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTService {

    private final String secretKey;

    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return  Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 30))
                .and()
                .signWith(getKey())
                .compact();

    }

    public String populateAuthorities(Collection<? extends GrantedAuthority> authorities){
         Set<String> authSet = new HashSet<>();
         for(GrantedAuthority authority : authorities){
             authSet.add(authority.getAuthority());
         }
         return String.join(",", authSet);
    }


    public String extractUsername(String token) {
        //String token = token.substring(7); // Assuming "Bearer " is removed from the token
        return extractClaim(token, Claims::getSubject);

    }

    public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {

        System.out.println(Jwts.parser().verifyWith(getKey()));
        Claims claims =  Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;
    }

    public boolean validateToken(String token, UserDetailsImpl userDetails) {
        String username = extractUsername(token);
        if(username.contains("@")){
            return (username.equals(userDetails.getEmail()) && !isTokenExpired(token));
        }
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}


