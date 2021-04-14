package com.back.ecom.tp.mapper;

import com.back.ecom.tp.dto.ArticleDTO;
import com.back.ecom.tp.entity.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface ArticleMapper {

    Article mapToEntity(ArticleDTO articleDTO);

    ArticleDTO mapToDTO(Article article);

    List<Article> mapToListOfEntity(List<ArticleDTO> articleDTOList);

    List<ArticleDTO> mapToListOfDTO(List<Article> articleList);
}
