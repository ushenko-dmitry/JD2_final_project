package ru.mail.dimaushenko.service.impl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.ArticleRepository;
import ru.mail.dimaushenko.repository.CommentRepository;
import ru.mail.dimaushenko.repository.UserRepository;
import static ru.mail.dimaushenko.repository.constants.SortOrderEnum.DESC;
import ru.mail.dimaushenko.repository.model.Article;
import ru.mail.dimaushenko.repository.model.Comment;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.ArticleService;
import ru.mail.dimaushenko.service.converter.ArticleConverter;
import ru.mail.dimaushenko.service.converter.ConverterFacade;
import ru.mail.dimaushenko.service.converter.PaginationConverter;
import ru.mail.dimaushenko.service.model.AddArticleDTO;
import ru.mail.dimaushenko.service.model.ArticleDTO;
import ru.mail.dimaushenko.service.model.ArticlePreviewDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ConverterFacade converterFacade;

    public ArticleServiceImpl(
            ArticleRepository articleRepository, 
            UserRepository userRepository, 
            CommentRepository commentRepository,
            ConverterFacade converterFacade
    ) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.converterFacade = converterFacade;
    }

    @Override
    public ArticleDTO addArticle(AddArticleDTO addArticleDTO) {
        ArticleConverter articleConverter = converterFacade.getArticleConverter();
        Article article = articleConverter.getObjectFromDTO(addArticleDTO);
        Long userId = addArticleDTO.getUserId();
        User user = userRepository.findById(userId);
        article.setUser(user);
        article.setDate(new Date(System.currentTimeMillis()));
        articleRepository.persist(article);
        return articleConverter.getDTOFromObject(article);
    }

    @Override
    public List<ArticlePreviewDTO> getArticlePreviews() {
        List<Article> articles = articleRepository.findAll();
        ArticleConverter articleConverter = converterFacade.getArticleConverter();
        return articleConverter.getArticlePreviewFromObject(articles);
    }

    @Override
    public List<ArticlePreviewDTO> getArticlePreviews(PaginationDTO paginationDTO) {
        PaginationConverter paginationConverter = converterFacade.getPaginationConverter();
        Pagination pagination = paginationConverter.getObjectFromDTO(paginationDTO);
        List<Article> articles = articleRepository.getArticlesSortByDate(pagination, DESC);
        ArticleConverter articleConverter = converterFacade.getArticleConverter();
        return articleConverter.getArticlePreviewFromObject(articles);
    }

    @Override
    public ArticleDTO getArticle(Long id) {
        Article article = articleRepository.findById(id);
        ArticleConverter articleConverter = converterFacade.getArticleConverter();
        return articleConverter.getDTOFromObject(article);
    }

    @Override
    public boolean deleteArticle(Long id) {
        Article article = articleRepository.findById(id);
        article.setUser(null);
        articleRepository.remove(article);
        return true;
    }

    @Override
    public Integer getAmountArticles() {
        return articleRepository.getAmountElements();
    }

    @Override
    public Boolean updateArticle(ArticleDTO articleDTO) {
        ArticleConverter articleConverter = converterFacade.getArticleConverter();
        Article updatedArticle = articleConverter.getObjectFromDTO(articleDTO);
        Article article = articleRepository.findById(articleDTO.getId());
        article.setTitle(updatedArticle.getTitle());
        article.setContent(updatedArticle.getContent());
        article.setDate(new Date(System.currentTimeMillis()));
        return true;
    }

    @Override
    public void deleteComment(Long articleId, Long commentId) {
        Article article = articleRepository.findById(articleId);
        Comment comment = commentRepository.findById(commentId);
        article.getComments().remove(comment);
        commentRepository.remove(comment);
    }

}
