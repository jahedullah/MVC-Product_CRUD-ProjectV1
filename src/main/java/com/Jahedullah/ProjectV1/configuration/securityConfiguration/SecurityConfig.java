package com.Jahedullah.ProjectV1.configuration.securityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.concurrent.TimeUnit;

import static com.Jahedullah.ProjectV1.configuration.permissions.AppUserPermission.PRODUCT_WRITE;
import static com.Jahedullah.ProjectV1.configuration.role.AppUserRole.ADMIN;
import static com.Jahedullah.ProjectV1.configuration.role.AppUserRole.USER;


@EnableWebSecurity(debug = true)
@ComponentScan(basePackages = "com.Jahedullah")

public class SecurityConfig{
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //these ant matchers are to test whether the id logged in is a USER or ADMIN.
                .antMatchers("/home").hasAnyAuthority()
                .antMatchers("/test/user").hasRole(USER.name())
                .antMatchers("/test/admin").hasRole(ADMIN.name())

                //these ant matchers are specifically bound to USERS and ADMINS. **Permission based ant-matchers. e.g. USERS can not DELETE OR UPDATE any Product.
                .antMatchers(HttpMethod.DELETE,"/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(HttpMethod.GET,"/Products/**").hasAnyRole(ADMIN.name(), USER.name())
                .antMatchers("/myCustomLogin").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                            .loginPage("/login").permitAll()
                            .defaultSuccessUrl("/process-login",true)
                .passwordParameter("password")
                .usernameParameter("username")

                .and()
                            .rememberMe()
                            .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
                            .key("somethingverysecured")
                            .rememberMeParameter("remember-me")

                .and()
                .logout()
                            .logoutUrl("/logout")
                            .clearAuthentication(true)
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID","remember-me")
                .and()
                .httpBasic();

        http.csrf().disable();

        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService(){

        UserDetails nayimUser = User.builder()
                .username("nayim")
                .password(passwordEncoder.encode("nayim123"))
//                .roles(USER.name())
                .authorities(USER.getGrantedAuthorities())
                .build();

        // In memory Admin user
        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
//                .roles(ADMIN.name()) //ROLE ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                adminUser,
                nayimUser
        );

    }

}
