package com.back.ecom.tp.security;

import com.back.ecom.tp.service.UtilisateurService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private JwtTokenProvider jwtTokenProvider;
    private UtilisateurService utilisateurService;

    public JwtConfigurer(JwtTokenProvider jwtTokenProvider, UtilisateurService utilisateurService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider, utilisateurService);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
