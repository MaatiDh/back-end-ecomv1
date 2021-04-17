package com.back.ecom.tp.security;

import com.back.ecom.tp.dao.UtilisateurRepository;
import com.back.ecom.tp.entity.Utilisateur;
import com.back.ecom.tp.exception.NotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CustomUserDetailsService implements UserDetailsService {
    private UtilisateurRepository utilisateurRepository;

    public CustomUserDetailsService(UtilisateurRepository users) {
        this.utilisateurRepository = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        return utilisateurRepository.findByLogin(username).orElseThrow(()
                -> new NotFoundException(Utilisateur.class, username));
    }
}
