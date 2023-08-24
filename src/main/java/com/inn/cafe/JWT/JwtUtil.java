package com.inn.cafe.JWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.base.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	private String secretKey =  "ashIshSaHu";
	
	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject); 
	}
	
	public Date extractExpirationDate(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	
	
	public <T> T extractClaims(String token, Function<Claims,T> claimResolver){
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
	
	
	private Boolean isTokenExpired (String token) {
		return extractExpirationDate(token).before(new Date());
	}
	
	
	public String generateToken(String 	userName, String role) {
		
		Map<String , Object> claims = new HashMap<>();
		claims.put("role",role);
		
									//subject is username
		return createToken(claims, userName);
		
	}
	
	//Generating a token
	private String createToken(Map<String,Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 10 ))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}
	
	public Boolean validateToken (String token , UserDetails userDetails) {
	
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}
}
