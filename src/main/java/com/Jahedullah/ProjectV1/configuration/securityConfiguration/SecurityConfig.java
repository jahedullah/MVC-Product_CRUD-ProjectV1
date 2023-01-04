package com.Jahedullah.ProjectV1.configuration.securityConfiguration;
import com.Jahedullah.ProjectV1.configuration.auth.ApplicationUserService;
import com.Jahedullah.ProjectV1.configuration.jwt.JwtTokenVerifier;
import com.Jahedullah.ProjectV1.configuration.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static com.Jahedullah.ProjectV1.configuration.permissions.AppUserPermission.PRODUCT_WRITE;
import static com.Jahedullah.ProjectV1.configuration.role.AppUserRole.ADMIN;
import static com.Jahedullah.ProjectV1.configuration.role.AppUserRole.USER;


@EnableWebSecurity(debug = true)
@ComponentScan(basePackages = "com.Jahedullah")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    public SecurityConfig(ApplicationUserService applicationUserService, PasswordEncoder passwordEncoder) {
        this.applicationUserService = applicationUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }



    // This Bean is Used when you are using CustomUserDetailsService -> which is used to fetch user Id and Pass and other infos from DB.
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);

        return  provider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager))
                // adding a filter after the "JwtUsernameAndPasswordAuthenticationFilter"
                .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.DELETE,"/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/Products/**").hasAuthority(PRODUCT_WRITE.getPermission())
                .antMatchers(HttpMethod.GET,"/Products/**").hasAnyRole(ADMIN.name(), USER.name())

                .anyRequest().authenticated();


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
