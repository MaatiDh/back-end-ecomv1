package com.back.ecom.tp.service;

import com.back.ecom.tp.dao.UtilisateurRepository;
import com.back.ecom.tp.dto.UtilisateurDTO;
import com.back.ecom.tp.entity.Utilisateur;
import com.back.ecom.tp.enums.Role;
import com.back.ecom.tp.exception.*;
import com.back.ecom.tp.mapper.UtilisateurMapper;
import com.back.ecom.tp.security.JwtTokenProvider;
import com.back.ecom.tp.util.email.EmailHtmlSender;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    private final UtilisateurMapper utilisateurMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final EmailHtmlSender emailHtmlSender;

    @Transactional
    public UtilisateurDTO findByNomUtilisateur(String nomUtilisateur) {
        if (nomUtilisateur == null) {
            throw new NullValueException(nomUtilisateur);
        }
        Utilisateur utilisateur = utilisateurRepository.findByNomUtilisateur(nomUtilisateur)
                .orElseThrow(() -> new NotFoundException(nomUtilisateur));
        return utilisateurMapper.mapToDTO(utilisateur);
    }

    @Transactional
    public UtilisateurDTO ajouterUtilisateur(UtilisateurDTO utilisateurDTO) {
        verificationChamps(utilisateurDTO);

        if (utilisateurRepository.findByEmail(utilisateurDTO.getEmail()).isPresent()) {
            throw new AlreadyExistException("email");
        }
        if (utilisateurRepository.findByNomUtilisateur(utilisateurDTO.getNomUtilisateur()).isPresent()) {
            throw new AlreadyExistException("nom utilisateur");
        }

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(utilisateurDTO.getEmail());
        if(!matcher.matches())
            throw new WrongEmailException();
        String password=utilisateurDTO.getPassword();
        utilisateurDTO.setPassword(passwordEncoder.encode(utilisateurDTO.getPassword()));
        utilisateurDTO.setRole(Role.ROLE_CLIENT);
        Utilisateur utilisateur = utilisateurRepository.save(utilisateurMapper.mapToEntity(utilisateurDTO));
        sendMail(utilisateurDTO.getEmail(),utilisateurDTO.getNomUtilisateur(),password);

        return utilisateurMapper.mapToDTO(utilisateur);
    }

    @Transactional
    public UtilisateurDTO authentification(UtilisateurDTO utilisateur) {
        if (utilisateur == null) {
            throw new NullValueException("utilisateur");
        }
        if (utilisateur.getNomUtilisateur() == null) {
            throw new NullValueException("login");
        }
        if (utilisateur.getPassword() == null) {
            throw new NullValueException("password");
        }
        String utilisateurname = utilisateur.getNomUtilisateur();
        UtilisateurDTO utilisateurDto = findByNomUtilisateur(utilisateurname);

        if (!passwordEncoder.matches(utilisateur.getPassword(), utilisateurDto.getPassword())) {
            throw new WrongPasswordException(Utilisateur.class);
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(utilisateurname, utilisateur.getPassword()));

        utilisateurDto.setToken(jwtTokenProvider.createToken(utilisateurname, findByNomUtilisateur(utilisateurname).getRole()));

        return utilisateurDto;
    }


    @Transactional(readOnly = false)
    public String changePassword(String nomUtilisateur, String currentPassword, String newPassword) {
        if (nomUtilisateur == null) {
            throw new NullValueException("nomUtilisateur");
        }
        if (currentPassword == null) {
            throw new NullValueException("currentPassword");
        }
        if (newPassword == null) {
            throw new NullValueException("newPassword");
        }
        Utilisateur utilisateur = utilisateurRepository.findByEmail(nomUtilisateur)
                .orElseThrow(() -> new NotFoundException(nomUtilisateur));
        if (passwordEncoder.matches(currentPassword, utilisateur.getPassword())) {
            utilisateur.setPassword(passwordEncoder.encode(newPassword));
            utilisateurRepository.save(utilisateur);

            return "{\"message\" : \"password changé avec succés\" }";
        } else {
            throw new WrongPasswordException(Utilisateur.class);
        }

    }


    private void verificationChamps(UtilisateurDTO utilisateurDTO) {
        if (utilisateurDTO == null) {
            throw new NullEntityException(Utilisateur.class);
        }

        List<String> nullFields = new ArrayList<>();

        if (utilisateurDTO.getEmail() == null || utilisateurDTO.getEmail().isEmpty()) {
            nullFields.add("email");
        }
        if (utilisateurDTO.getNom() == null || utilisateurDTO.getNom().isEmpty()) {
            nullFields.add("Nom");
        }
        if (utilisateurDTO.getNomUtilisateur() == null || utilisateurDTO.getNomUtilisateur().isEmpty()) {
            nullFields.add("Nom Utilisateur");
        }
        if (utilisateurDTO.getPrenom() == null || utilisateurDTO.getPrenom().isEmpty()) {
            nullFields.add("Prenom");
        }
        if (utilisateurDTO.getPassword() == null || utilisateurDTO.getPassword().isEmpty()) {
            nullFields.add("Password");
        }
        if (utilisateurDTO.getTel() == null || utilisateurDTO.getTel().isEmpty()) {
            nullFields.add("Tel");
        }
        if (utilisateurDTO.getVille() == null || utilisateurDTO.getVille().isEmpty()) {
            nullFields.add("Ville");
        }

        if (!nullFields.isEmpty()) {
            throw new NullValueException(nullFields);
        }
    }

    private void sendMail(String email, String username,String password) {
        final Context ctx = new Context();
        ctx.setVariable("password", password);
        ctx.setVariable("username", username);
        emailHtmlSender.send(email, "nouveau compte",
                "email/email", ctx,null);
    }
}
