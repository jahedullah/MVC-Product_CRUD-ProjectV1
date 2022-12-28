package com.Jahedullah.ProjectV1.configuration.securityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static com.Jahedullah.ProjectV1.configuration.role.AppUserRole.ADMIN;
import static com.Jahedullah.ProjectV1.configuration.role.AppUserRole.USER;

@EnableWebSecurity(debug = true)
@ComponentScan(basePackages = "com.Jahedullah")

public class SecurityConfig{
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
//    AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider =
//                new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//
//        return provider;
//
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/home").hasAnyAuthority()
                .antMatchers("/test/user").hasRole(USER.name())
                .antMatchers("/test/admin").hasRole(ADMIN.name())
                .antMatchers(
                        "/admin",
                        "/ProductsAdd",
                        "/ProductsUpdate/{courseId}",
                        "/ProductsDelete/{courseId}").hasAuthority("ADMIN")
                .antMatchers("/myCustomLogin").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/myCustomLogin")
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
                .roles(USER.name())
                .build();

        // In memory Admin user
        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles(ADMIN.name()) //ROLE ADMIN
                .build();

        return new InMemoryUserDetailsManager(
                adminUser,
                nayimUser
        );

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("projectv1")
//                .password("{bcrypt}$2a$12$/GP1BR1zGqmnNU.riYP1o.z5QTzYB2bMrL/gLCc8.odrkf5ctCYPe") //Encoded String for "projectv1123"
//                .roles("admin");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/myCustomLogin").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/myCustomLogin")
//                .and()
//                .httpBasic()
//                .and().csrf().disable();
//
//    }


}
