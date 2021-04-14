package com.back.ecom.tp.dao.specification;

import com.back.ecom.tp.entity.Article;
import org.springframework.data.jpa.domain.Specification;

public class ArticleSpecification {

    public static Specification<Article> findBy(String property, String value) {
        return (root, query, cb) -> {
            if (value == null) {
                return cb.conjunction();
            }
            return cb.like(root.<String>get(property), "%"+value+"%");

        };
    }
}
