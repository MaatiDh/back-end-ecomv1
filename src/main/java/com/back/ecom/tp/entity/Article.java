package com.back.ecom.tp.entity;

import lombok.*;
import javax.persistence.*;

import static com.back.ecom.tp.constant.ColumnNameConstants.*;
import static com.back.ecom.tp.constant.TableNameConstants.ARTICLE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = ARTICLE)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = REFERENCE_ARTICLE)
    private String referenceArticle;

    @Column(name = DESIGNATION,unique = true)
    private String designation;

    @Column(name = PRIX)
    private Double prix;

    @Column(name = STOCK)
    private Integer stock;
}
