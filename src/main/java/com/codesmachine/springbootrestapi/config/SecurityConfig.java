package com.codesmachine.springbootrestapi.config;

import com.codesmachine.springbootrestapi.security.JwtAuthenticationEntryPoint;
import com.codesmachine.springbootrestapi.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.http.HttpClient;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {



    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;




    // this will help us to do the database authantication using user details service & password encoder
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // this will help us to do encryption or decryption of the user password
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // http basic
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
                        //authorize.anyRequest().authenticated()
                        authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults())
//                These codes below are related to JWT Token
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                ).sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // apply jwt authentication fitler before spring boot security
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // creating sample of users to use in memory
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails cody = User.builder()
//                .username("cody")
//                .password(passwordEncoder().encode("123"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//    return new InMemoryUserDetailsManager(cody,admin);
//    }
}
