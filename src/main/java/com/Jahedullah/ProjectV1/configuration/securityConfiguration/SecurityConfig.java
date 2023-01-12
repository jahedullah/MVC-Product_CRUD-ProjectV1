package com.Jahedullah.ProjectV1.configuration.securityConfiguration;

import com.Jahedullah.ProjectV1.configuration.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static com.Jahedullah.ProjectV1.model.rolesAndPermissions.AppUserPermission.PRODUCT_WRITE;
import static com.Jahedullah.ProjectV1.model.rolesAndPermissions.AppUserRole.*;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/Users/User").permitAll()
                .antMatchers("/Users").permitAll()
                .antMatchers(GET,"/Products").permitAll()
                .antMatchers(GET, "/Products/**").permitAll()

                .antMatchers(DELETE, "/Admin/**").hasAnyRole(ADMIN.name(), SUPER_ADMIN.name())
                .antMatchers(POST, "/Users/Admin").hasRole(SUPER_ADMIN.name())
                .antMatchers(DELETE, "/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(PUT, "/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(POST, "/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())




                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);


        http.csrf().disable();

        return http.build();
    }

}
