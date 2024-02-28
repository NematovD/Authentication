package com.example.basicauthentication.config;

import com.example.basicauthentication.enums.Permission;
import com.example.basicauthentication.enums.Role;
import com.example.basicauthentication.service.MyUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class securityConfig {
    private final MyUserDetailsServiceImpl myUserDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity.csrf(AbstractHttpConfigurer::disable)
                 .authorizeHttpRequests(request ->
                         request.requestMatchers("/api/auth").permitAll()
                                 .requestMatchers("/api/admin").hasRole(Role.ADMIN.name())
                                 .requestMatchers("/api/home").hasAnyRole(Role.ADMIN.name(),Role.USER.name())
                                 .requestMatchers(HttpMethod.POST,"/api/product").hasAnyAuthority(Permission.CREATE.name())
                                 .requestMatchers(HttpMethod.PUT,"/api/product").hasAnyAuthority(Permission.UPDATE.name())
                                 .requestMatchers(HttpMethod.GET,"/api/product").hasAnyAuthority(Permission.READ.name())
                                 .requestMatchers(HttpMethod.DELETE,"/api/product").hasAnyAuthority(Permission.DELETE.name())
                                 .anyRequest().authenticated())
                 .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }



    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return  configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
