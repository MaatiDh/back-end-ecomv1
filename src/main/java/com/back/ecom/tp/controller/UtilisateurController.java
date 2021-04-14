package com.back.ecom.tp.controller;

import com.back.ecom.tp.dto.UtilisateurDTO;
import com.back.ecom.tp.dto.objHandle.ChangePassword;
import com.back.ecom.tp.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.back.ecom.tp.constant.PathConstants.UTILISATEUR;
import static com.back.ecom.tp.util.AuthenticationUtils.getUsername;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(UTILISATEUR)
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<UtilisateurDTO> ajouterUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(utilisateurService.ajouterUtilisateur(utilisateurDTO));
    }

    @PostMapping(value = "/authentification")
    public ResponseEntity<UtilisateurDTO> authentification(@RequestBody UtilisateurDTO utilisateurDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(utilisateurService.authentification(utilisateurDTO));
    }

    @PutMapping(value = "/changepassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassword changePassword) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(utilisateurService.changePassword(getUsername(),
                        changePassword.getCurrentPassword(), changePassword.getNewPassword()));
    }
}
