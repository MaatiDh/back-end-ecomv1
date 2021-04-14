package com.back.ecom.tp.entity;

import lombok.*;
import javax.persistence.*;
import static com.back.ecom.tp.constant.ColumnNameConstants.*;
import static com.back.ecom.tp.constant.TableNameConstants.PANIER;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = PANIER)
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JoinColumn(name= CLIENT_ID)
    private Utilisateur client;

    @ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JoinColumn(name= ARTICLE_ID)
    private Article article;

    @Column(name = QUANTITE)
    private Integer quantite;
}
