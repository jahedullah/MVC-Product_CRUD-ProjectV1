package com.Jahedullah.ProjectV1.configuration.securityConfiguration;

import com.Jahedullah.ProjectV1.configuration.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.Jahedullah.ProjectV1.entity.permissions.AppUserPermission.PRODUCT_WRITE;
import static com.Jahedullah.ProjectV1.entity.role.AppUserRole.ADMIN;
import static com.Jahedullah.ProjectV1.entity.role.AppUserRole.USER;

@Configuration
@EnableWebSecurity(debug = true)
//@ComponentScan(basePackages = "com.Jahedullah")
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
//                .addFilterAfter(new JwtTokenFilter(), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .antMatchers("/auth/**").permitAll()

                .antMatchers(HttpMethod.DELETE, "/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/Products/**").hasAnyRole(ADMIN.name(), USER.name())

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
