package com.Jahedullah.ProjectV1.configuration.securityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity(debug = true)
@ComponentScan(basePackages = "com.Jahedullah")

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);

        return provider;

    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home").hasAuthority("USER")
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/myCustomLogin").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/myCustomLogin")
                .and()
                .httpBasic();

        http.csrf().disable();


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
