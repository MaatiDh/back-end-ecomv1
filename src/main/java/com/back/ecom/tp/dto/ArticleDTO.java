package com.back.ecom.tp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {

    private Long id;

    private String referenceArticle;

    private String designation;

    private Double prix;

    private Integer stock;
}
