package ru.mail.dimaushenko.service.converter;

import java.util.List;
import ru.mail.dimaushenko.repository.model.Article;
import ru.mail.dimaushenko.service.model.AddArticleDTO;
import ru.mail.dimaushenko.service.model.ArticleDTO;
import ru.mail.dimaushenko.service.model.ArticlePreviewDTO;

public interface ArticleConverter extends GeneralConverter<ArticleDTO, Article> {

    Article getObjectFromDTO(AddArticleDTO addArticleDTO);

    ArticlePreviewDTO getArticlePreviewFromObject(Article article);

    List<ArticlePreviewDTO> getArticlePreviewFromObject(List<Article> articles);

}
