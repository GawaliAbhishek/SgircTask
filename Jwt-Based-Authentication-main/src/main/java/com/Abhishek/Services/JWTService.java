package com.Abhishek.Services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private static final String SECRET_KEY="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public String extractUsername(String token) {
       System.out.println("Extarct Username");
       System.out.println(token);
        return extractClaim(token, Claims::getSubject);
    }

    public String genrateToken(UserDetails userDetails){
        return genrateToken(new HashMap<>(), userDetails);
    }

    public String genrateToken(Map<String,Object> extraClaims,UserDetails userDetails){
       // byte[] keyByte=Base64.getEncoder().encode(SECRET_KEY.getBytes());
 // System.out.println(getSignInKey().toString());

        return Jwts.builder()
                    .setClaims(extraClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 60 *24))
                    .signWith(getSignInKey() ,SignatureAlgorithm.HS256)
                    .compact();

    }

    public boolean isTokenVaild(String token,UserDetails userDetails){
        //System.out.println("Is Valid");
       //System.out.println(token);
        final String username=extractUsername(token);
       // System.out.println(username);
        return 
        username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpression(token).before(new Date());
    }

    private Date extractExpression(String token) {
        return extractClaim(token, Claims::getExpiration)
        ;
    }

    // Extract one Claim
    public <T> T extractClaim(String token,Function<Claims,T> claimResolver){
        // System.out.println("Extract Claim");
        // System.out.println(token);
        final Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }




    // Extarct all claims from token
    public Claims extractAllClaims(String token){
        // System.out.println("Extract All claims");
        // System.out.println(token);
        Claims c= Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

                  return c;  
                
    }

    // generate the SECRET_KEY and Decode it for security purpose
    private Key getSignInKey() {
        byte[] keyByte=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);  
    }


    
}
