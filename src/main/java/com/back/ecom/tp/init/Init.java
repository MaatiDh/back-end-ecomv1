package com.back.ecom.tp.init;

import com.back.ecom.tp.dao.ArticleRepository;
import com.back.ecom.tp.entity.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class Init {
    @Autowired
    ArticleRepository articleRepository;
    @PostConstruct
    public void init() {
        articleRepository.save(Article.builder().designation("test").referenceArticle("test").prix(1000D).stock(15)
                .isEditable(Boolean.FALSE).build());
    }

}
