package ecommerce.v1.configurations.components.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    //secret key jwt
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655469500563388576D5A71347437";
    //algorithm jwt
    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(this.SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    //create token jwt
    private String createToken(Map<String, Object> claims, String userName) /*combine 01*/{
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))//this  1800000 equals 30 second
                .signWith(this.getSignKey(), SignatureAlgorithm.HS256).compact();
    }
    //generate token jwt
    public String generateToken(String userName)/*combine 01*/{
        Map<String,Object> claims=new HashMap<>();
        return this.createToken(claims,userName);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractUsername(String token) {
        return this.extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token) {
        return this.extractClaim(token, Claims::getExpiration);
    }
    private Boolean isTokenExpired(String token) {
        return this.extractExpiration(token).before(new Date());
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = this.extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !this.isTokenExpired(token));
    }


}
