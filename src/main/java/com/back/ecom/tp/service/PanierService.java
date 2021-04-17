package com.back.ecom.tp.service;

import com.back.ecom.tp.dao.ArticleRepository;
import com.back.ecom.tp.dao.PanierRepository;
import com.back.ecom.tp.dao.UtilisateurRepository;
import com.back.ecom.tp.dto.PanierDTO;
import com.back.ecom.tp.entity.Article;
import com.back.ecom.tp.entity.Panier;
import com.back.ecom.tp.entity.Utilisateur;
import com.back.ecom.tp.exception.*;
import com.back.ecom.tp.mapper.PanierMapper;
import com.back.ecom.tp.util.email.EmailHtmlSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PanierService {

    private final PanierMapper panierMapper;
    private final PanierRepository panierRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final EmailHtmlSender emailHtmlSender;
    private final ArticleRepository articleRepository;

    @Transactional
    public PanierDTO ajouterAuPanier(Long articleId,String nomUtilisateur,Integer quantite){
        Utilisateur utilisateur=utilisateurRepository.findByEmail(nomUtilisateur)
                .orElseThrow(()-> new NotFoundException("nomUtilisateur"));
        List<Panier> paniers=panierRepository.getByClient_Email(nomUtilisateur);

        Article article=articleRepository.findById(articleId)
                .orElseThrow(()-> new NotFoundException("article"));

        if(quantite>article.getStock())
            throw new StockException();

        List<Article> articleList=paniers.stream().map(Panier::getArticle).collect(Collectors.toList());
        if(articleList.contains(article))
            throw new AlreadyExistException("deja ajouter au panier");
        else {
            Panier panier=new Panier();
            panier.setClient(utilisateur);
            panier.setQuantite(quantite);
            article.setStock(article.getStock() - quantite);
            panier.setArticle(article);
            return panierMapper.mapToDTO(panierRepository.save(panier));
        }
    }

    @Transactional
    public List<PanierDTO> getMyCart(String nomUtilisateur){
        List<Panier> paniers=panierRepository.getByClient_Email(nomUtilisateur);

        return panierMapper.mapToListOfDTO(paniers);
    }

    @Transactional
    public void validateCart(String nomUtilisateur){
        List<Panier> paniers=panierRepository.getByClient_Email(nomUtilisateur);
        AtomicReference<Double> prix= new AtomicReference<>(0D);
        paniers.forEach(panier -> {
            prix.set(prix.get() + (panier.getQuantite() * panier.getArticle().getPrix()));
        });
        sendMail(nomUtilisateur,prix.get());
        panierRepository.deleteAll(paniers);
    }

    private void sendMail(String email, Double prix) {
        final Context ctx = new Context();
        ctx.setVariable("prix", prix);
        emailHtmlSender.send(email, "panier",
                "email/panier", ctx,null);
    }
}
