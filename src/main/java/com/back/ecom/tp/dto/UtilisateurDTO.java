package com.back.ecom.tp.dto;

import com.back.ecom.tp.entity.Panier;
import com.back.ecom.tp.enums.Role;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {

    private Long id;

    private String nom;

    private String prenom;

    private String nomUtilisateur;

    private String email;

//    @JsonIgnore
    private String password;

    private String ville;

    private String tel;

    private Role role;

    private String token;

    private Panier panier;
}
