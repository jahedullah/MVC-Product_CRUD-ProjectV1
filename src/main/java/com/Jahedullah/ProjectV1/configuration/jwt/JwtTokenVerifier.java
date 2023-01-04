package com.Jahedullah.ProjectV1.configuration.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class JwtTokenVerifier  extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Grabbing the Bearer Token that is tagged as "Authorization" in the Header.
        String authorizationHeader = request.getHeader("Authorization");

        // Checking if the authorizationHeader is empty or holding any token that does not starts with Bearer.
        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            // The Request will be rejected.
            filterChain.doFilter(request,response);
            return;
        }
        // Cutting the "Bearer Token " String out of the token. Basically storing the actual token.
        String token = authorizationHeader.replace("Bearer Token ", "");
        try{
            // the same key that we made in "JwtUsernameAndPasswordAuthenticationFilter" class.
            String secretKey = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";

            // JWS is the Signed JWT
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            // In the subject field we have the username. After decoding the key.
            String username = body.getSubject();

            // Grabbing the authorities part and storing it in a map.
            List<Map<String, String>> authorities = (List<Map<String,String>>)body.get("authorities");

            // storing it in SimpleGrantedAuthorities.
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            // giving the information to the authenticator.
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );
            // the client that has sent the token is now authenticated.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e){
            throw new IllegalStateException(String.format("Token [ %s ] cannot be trusted", token));
        }

        // pass the response to next filter-chain if there is any to make the api being executed and pass the data.
        filterChain.doFilter(request,response);

    }
}
