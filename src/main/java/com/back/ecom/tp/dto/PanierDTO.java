package com.back.ecom.tp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PanierDTO {

    private Long id;

    private UtilisateurDTO client;

    private ArticleDTO article;

    private Integer quantite;
}
