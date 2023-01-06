package com.Jahedullah.ProjectV1.configuration.jwt;

import com.Jahedullah.ProjectV1.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtTokenVerifier  extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Grabbing the Bearer Token that is tagged as "Authorization" in the Header.
        final String authHeader = request.getHeader("Authorization");
        // Cutting the "Bearer Token " String out of the token. Basically storing the actual token.
        final String jwt;
        // To Extract the user Email from the database.
        final String userEmail;

        // Checking if the authorizationHeader is empty or holding any token that does not starts with Bearer.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // The Request will be rejected.
            filterChain.doFilter(request, response);
            return;
        }
        // Because "Bearer Token " String character counts upto 13. So the real token appears from index 13.
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        // if we have the userEmail and the user is not Authenticated Yet.
        if (userEmail != null
                &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            System.out.println("yeah");
            //Check if the user is Valid or Not.
            if (jwtService.isTokenValid(jwt, userDetails)) {
                //if valid then create a token of type "UsernamePasswordAuthenticationToken"
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }

        // pass the response to next filter-chain if there is any to make the api being executed and pass the data.
        filterChain.doFilter(request, response);

    }
}
