package com.back.ecom.tp.service;

import com.back.ecom.tp.dao.ArticleRepository;
import com.back.ecom.tp.dao.PanierRepository;
import com.back.ecom.tp.dao.specification.ArticleSpecification;
import com.back.ecom.tp.dto.ArticleDTO;
import com.back.ecom.tp.entity.Article;
import com.back.ecom.tp.entity.Utilisateur;
import com.back.ecom.tp.exception.*;
import com.back.ecom.tp.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final ArticleMapper articleMapper;

    private final PanierRepository panierRepository;

    @Transactional
    public List<ArticleDTO> rechercheArticle(String recherche){
        List<Article> articleList;
        if (recherche!=null || recherche !="") {
            articleList = articleRepository.findAll(Specification.where(
                    ArticleSpecification.findBy("referenceArticle", recherche)
                            .and(ArticleSpecification.findBy("designation", recherche))
            ));
        }else {
            articleList = articleRepository.findAll();
        }
        return articleMapper.mapToListOfDTO(articleList);
    }

    @Transactional
    public ArticleDTO getArticleById(Long articleId){
        if (articleId == null) {
            throw new NullValueException("article Id");
        }
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("Article"));
        return articleMapper.mapToDTO(article);
    }

    @Transactional
    public ArticleDTO ajouterArticle(ArticleDTO articleDTO){
        verificationChamps(articleDTO);
        if(articleRepository.findByDesignation(articleDTO.getDesignation()).isPresent())
            throw new AlreadyExistException("Designation");

        return articleMapper.mapToDTO(articleRepository.save(articleMapper.mapToEntity(articleDTO)));
    }
    @Transactional
    public List<ArticleDTO> ajouterArticles(List<ArticleDTO> articleDTOs){

        //todo check fiels validation
        return articleMapper.mapToListOfDTO(articleRepository.saveAll(articleMapper.mapToListOfEntity(articleDTOs)));
    }

    @Transactional
    public ArticleDTO modifierArticle(Long articleId,ArticleDTO articleDTO){
        verificationChamps(articleDTO);
        ArticleDTO articleCheck=getArticleById(articleId);

        if(!articleCheck.getReferenceArticle().equals(articleDTO.getReferenceArticle()))
            throw new ValueCannotChangeException("Reference");

        return articleMapper.mapToDTO(articleRepository.save(articleMapper.mapToEntity(articleDTO)));
    }

    @Transactional
    public void supprimerArticle(Long articleId){
        getArticleById(articleId);
        int panierList=panierRepository.getByArticle_Id(articleId).size();
        if (panierList!=0)
            articleRepository.deleteById(articleId);
        else
            throw new ProductCannotBeDeletedException();
    }

    private void verificationChamps(ArticleDTO articleDTO) {
        if (articleDTO == null) {
            throw new NullEntityException(Utilisateur.class);
        }

        List<String> nullFields = new ArrayList<>();

        if (articleDTO.getDesignation() == null || articleDTO.getDesignation().isEmpty()) {
            nullFields.add("Designation");
        }
        if (articleDTO.getReferenceArticle() == null || articleDTO.getReferenceArticle().isEmpty()) {
            nullFields.add("Reference Article");
        }
        if (articleDTO.getPrix() == null) {
            nullFields.add("Prix");
        }
        if (articleDTO.getStock() == null) {
            nullFields.add("Stock");
        }

        if (!nullFields.isEmpty()) {
            throw new NullValueException(nullFields);
        }
    }
}
