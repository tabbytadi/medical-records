package com.example.medicalrecords.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakAuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        // Print user information
        String username = jwt.getClaimAsString("preferred_username");
        String name = jwt.getClaimAsString("name");
        String email = jwt.getClaimAsString("email");

        System.out.println("\n===== User Authentication Details =====");
        System.out.println("Username: " + username);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("JWT Claims: " + jwt.getClaims().keySet());

        // Extract and print roles
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        List<String> roles = new ArrayList<>();

        if (realmAccess != null && realmAccess.containsKey("roles")) {
            roles = (List<String>) realmAccess.get("roles");
        }

        System.out.println("Roles: " + roles);
        System.out.println("=====================================\n");

        // Convert roles to authorities
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }
}