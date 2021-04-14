package com.back.ecom.tp.controller;

import com.back.ecom.tp.dto.ArticleDTO;
import com.back.ecom.tp.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.back.ecom.tp.constant.PathConstants.ARTICLE;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(ARTICLE)
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
    public ResponseEntity<List<ArticleDTO>> rechercheArticle(@RequestParam(value = "recherche") String recherche) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(articleService.rechercheArticle(recherche));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT','ROLE_ADMINISTRATEUR')")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(articleService.getArticleById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<ArticleDTO> ajouterArticle(@RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(articleService.ajouterArticle(articleDTO));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<ArticleDTO> modifierArticle(@PathVariable Long id,@RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(articleService.modifierArticle(id,articleDTO));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATEUR')")
    public ResponseEntity<Void> modifierArticle(@PathVariable Long id) {
        articleService.supprimerArticle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
