package com.Jahedullah.ProjectV1.configuration.securityConfiguration;
import com.Jahedullah.ProjectV1.configuration.jwt.JwtTokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.Jahedullah.ProjectV1.entity.permissions.AppUserPermission.PRODUCT_WRITE;
import static com.Jahedullah.ProjectV1.entity.role.AppUserRole.ADMIN;
import static com.Jahedullah.ProjectV1.entity.role.AppUserRole.USER;

@Configuration
@EnableWebSecurity(debug = true)
//@ComponentScan(basePackages = "com.Jahedullah")
@RequiredArgsConstructor
public class SecurityConfig{

    private final JwtTokenVerifier jwtTokenVerifier;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
//                .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .antMatchers("/auth/**").permitAll()

                .antMatchers(HttpMethod.DELETE,"/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(HttpMethod.GET,"/Products/**").hasAnyRole(ADMIN.name(), USER.name())

                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtTokenVerifier, UsernamePasswordAuthenticationFilter.class);




        http.csrf().disable();

        return http.build();
    }




//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                //these ant matchers are to test whether the id logged in is a USER or ADMIN.
//                .antMatchers("/home").hasAnyAuthority()
//
//                //these ant matchers are specifically bound to USERS and ADMINS. **Permission based ant-matchers. e.g. USERS can not DELETE OR UPDATE any Product.
//                .antMatchers(HttpMethod.DELETE,"/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/Products/**").hasAnyRole(ADMIN.name(), USER.name())
//                .antMatchers("/myCustomLogin").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                            .loginPage("/login").permitAll()
//                            .defaultSuccessUrl("/process-login",true)
//                .passwordParameter("password")
//                .usernameParameter("username")
//
//                .and()
//                            .rememberMe()
//                            .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
//                            .key("somethingverysecured")
//                            .rememberMeParameter("remember-me")
//
//                .and()
//                .logout()
//                            .logoutUrl("/logout")
//                            .clearAuthentication(true)
//                            .invalidateHttpSession(true)
//                            .deleteCookies("JSESSIONID","remember-me")
//                .and()
//                .httpBasic();
//
//        http.csrf().disable();
//
//        return http.build();
//    }


    // This Bean is Used for Hardcoded USER Information. e.g. InMemoryDetailsService.


//    @Bean
//    protected UserDetailsService userDetailsService(){
//
//        UserDetails nayimUser = User.builder()
//                .username("nayim")
//                .password(passwordEncoder.encode("nayim123"))
////                .roles(USER.name())
//                .authorities(USER.getGrantedAuthorities())
//                .build();
//
//        // In memory Admin user
//        UserDetails adminUser = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin123"))
////                .roles(ADMIN.name()) //ROLE ADMIN
//                .authorities(ADMIN.getGrantedAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(
//                adminUser,
//                nayimUser
//        );
//
//    }



}
