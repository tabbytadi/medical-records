package com.example.medicalrecords.config;

import com.example.medicalrecords.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@AllArgsConstructor
public class SecurityConfig {
    private final UserService userService;

    @Bean
    public JwtDecoder jwtDecoder() {
        String issuerUri = "http://localhost:8080/realms/medical-records-realm";
        return JwtDecoders.fromIssuerLocation(issuerUri);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakAuthorityConverter());
        return jwtAuthenticationConverter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/doctors/**").hasAuthority("doctor")
//                        .requestMatchers("/api/doctors/**").hasAuthority("doctor")
//                        .requestMatchers("/examinations/**").hasAuthority("doctor")
//                        .requestMatchers("/api/examinations/**").hasAuthority("doctor")
//                        .requestMatchers("/medicines/**").hasAuthority("doctor")
//                        .requestMatchers("/api/medicines/**").hasAuthority("doctor")
//
//                        .requestMatchers(HttpMethod.GET, "/doctors").hasAuthority("patient")
//                        .requestMatchers(HttpMethod.GET, "/api/doctors").hasAuthority("patient")
//                        .requestMatchers(HttpMethod.GET, "/medicines").hasAuthority("patient")
//                        .requestMatchers(HttpMethod.GET, "/api/medicines").hasAuthority("patient")
//                        .requestMatchers(HttpMethod.GET, "/examinations").hasAuthority("patient")
//                        .requestMatchers(HttpMethod.GET, "/api/examinations").hasAuthority("patient")

                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("http://localhost:8080/realms/medical-records-realm/protocol/openid-connect/logout?redirect_uri=http://localhost:8083/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtCustomizer -> jwtCustomizer.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}


//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//            .authorizeHttpRequests(authz -> authz
//                    .anyRequest().permitAll() // Allow ALL requests without authorization
//            )
//            .oauth2ResourceServer(oauth2 -> oauth2
//                    .jwt(Customizer.withDefaults()) // Still validate JWT tokens
//            );
//    return http.build();
//}