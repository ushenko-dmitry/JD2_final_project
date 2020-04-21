package ru.mail.dimaushenko.service;

import java.util.List;
import ru.mail.dimaushenko.service.model.AddArticleDTO;
import ru.mail.dimaushenko.service.model.ArticleDTO;
import ru.mail.dimaushenko.service.model.ArticlePreviewDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;

public interface ArticleService {

    ArticleDTO addArticle(AddArticleDTO addArticleDTO);

    List<ArticlePreviewDTO> getArticlePreviews();

    List<ArticlePreviewDTO> getArticlePreviews(PaginationDTO pagination);

    ArticleDTO getArticle(Long id);

    boolean deleteArticle(Long id);

    Integer getAmountArticles();

}
