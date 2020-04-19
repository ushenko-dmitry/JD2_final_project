package ru.mail.dimaushenko.service.converter.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.Article;
import static ru.mail.dimaushenko.service.constants.ArticleConstants.CONTENT_PREVIEW_LENGTH;
import ru.mail.dimaushenko.service.converter.ArticleConverter;
import ru.mail.dimaushenko.service.converter.CommentConverter;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.model.AddArticleDTO;
import ru.mail.dimaushenko.service.model.ArticleDTO;
import ru.mail.dimaushenko.service.model.ArticlePreviewDTO;
import ru.mail.dimaushenko.service.model.CommentArticleDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

@Component
public class ArticleConverterImpl implements ArticleConverter {

    private final UserConverter userConverter;
    private final CommentConverter commentConverter;

    public ArticleConverterImpl(
            UserConverter userConverter,
            CommentConverter commentConverter
    ) {
        this.userConverter = userConverter;
        this.commentConverter = commentConverter;
    }

    @Override
    public ArticleDTO getDTOFromObject(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        UserDTO userDTO = userConverter.getDTOFromObject(article.getUser());
        articleDTO.setUserDetails(userDTO.getUserDetails());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setDate(article.getDate());
        List<CommentArticleDTO> commentDTOs = commentConverter.getCommentArticleFromObject(article.getComments());
        articleDTO.setComments(commentDTOs);
        return articleDTO;
    }

    @Override
    public List<ArticleDTO> getDTOFromObject(List<Article> articles) {
        List<ArticleDTO> articleDTOs = new ArrayList<>();
        for (Article article : articles) {
            articleDTOs.add(getDTOFromObject(article));
        }
        return articleDTOs;
    }

    @Override
    public Article getObjectFromDTO(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setDate(articleDTO.getDate());
        return article;
    }

    @Override
    public List<Article> getObjectFromDTO(List<ArticleDTO> articleDTOs) {
        List<Article> articles = new ArrayList<>();
        for (ArticleDTO articleDTO : articleDTOs) {
            articles.add(getObjectFromDTO(articleDTO));
        }
        return articles;
    }

    @Override
    public Article getObjectFromDTO(AddArticleDTO articleDTO) {
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        return article;
    }

    @Override
    public ArticlePreviewDTO getArticlePreviewFromObject(Article article) {
        ArticlePreviewDTO articlePreviewDTO = new ArticlePreviewDTO();
        articlePreviewDTO.setId(article.getId());
        articlePreviewDTO.setTitle(article.getTitle());
        if (article.getContent().length() >= CONTENT_PREVIEW_LENGTH) {
            articlePreviewDTO.setContent(article.getContent().substring(0, CONTENT_PREVIEW_LENGTH));
        } else {
            articlePreviewDTO.setContent(article.getContent());
        }
        articlePreviewDTO.setDate(article.getDate());
        UserDTO userDTO = userConverter.getDTOFromObject(article.getUser());
        articlePreviewDTO.setUserDetails(userDTO.getUserDetails());
        return articlePreviewDTO;
    }

    @Override
    public List<ArticlePreviewDTO> getArticlePreviewFromObject(List<Article> articles) {
        List<ArticlePreviewDTO> articleDTOs = new ArrayList<>();
        for (Article article : articles) {
            articleDTOs.add(getArticlePreviewFromObject(article));
        }
        return articleDTOs;
    }

}
