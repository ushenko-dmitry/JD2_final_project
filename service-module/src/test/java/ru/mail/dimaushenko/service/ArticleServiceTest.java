package ru.mail.dimaushenko.service;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import ru.mail.dimaushenko.repository.ArticleRepository;
import ru.mail.dimaushenko.repository.CommentRepository;
import ru.mail.dimaushenko.repository.UserRepository;
import ru.mail.dimaushenko.repository.model.Article;
import ru.mail.dimaushenko.service.converter.ArticleConverter;
import ru.mail.dimaushenko.service.converter.ConverterFacade;
import ru.mail.dimaushenko.service.impl.ArticleServiceImpl;
import ru.mail.dimaushenko.service.model.AddArticleDTO;
import ru.mail.dimaushenko.service.model.ArticleDTO;
import ru.mail.dimaushenko.service.model.ArticlePreviewDTO;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceTest {

    private static final String ARTICLE_CONTENT_VALID = "Content";
    private static final String ARTICLE_TITLE_VALID = "Title";

    private ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private ConverterFacade converterFacade;
    @Mock
    private ArticleConverter articleConverter;

    @Before
    public void setup() {
        this.articleService = new ArticleServiceImpl(
                articleRepository,
                userRepository,
                commentRepository,
                converterFacade
        );

        when(converterFacade.getArticleConverter()).thenReturn(articleConverter);

    }

    @Test
    public void testAddArticle_returnNotNull() {
        AddArticleDTO addArticleDTO = setupValidAddArticle();
        ArticleDTO returnArticleDTO = setupArticleDTO(addArticleDTO);

        Article article = new Article();
        article.setTitle(addArticleDTO.getTitle());
        article.setContent(addArticleDTO.getContent());

        when(articleConverter.getObjectFromDTO(addArticleDTO)).thenReturn(article);

        when(articleService.addArticle(addArticleDTO)).thenReturn(returnArticleDTO);
        ArticleDTO articleDTO = articleService.addArticle(addArticleDTO);
        assertThat(articleDTO).isNotNull();
    }

    @Test
    public void testAddArticle_saveArticle() {
        AddArticleDTO addArticleDTO = setupValidAddArticle();
        ArticleDTO returnArticleDTO = setupArticleDTO(addArticleDTO);

        Article article = new Article();
        article.setTitle(addArticleDTO.getTitle());
        article.setContent(addArticleDTO.getContent());

        when(articleConverter.getObjectFromDTO(addArticleDTO)).thenReturn(article);
        when(articleConverter.getDTOFromObject(article)).thenReturn(returnArticleDTO);
        ArticleDTO articleDTO = articleService.addArticle(addArticleDTO);
        verify(articleRepository, times(1)).persist(article);
        assertThat(articleDTO).isEqualTo(returnArticleDTO);
    }

    @Test
    public void testGetArticlePreviews_returnNotNull() {
        List<ArticlePreviewDTO> returnArticlePreviewDTOs = new ArrayList<>();
        List<Article> articles = new ArrayList<>();
        when(articleRepository.findAll()).thenReturn(articles);
        when(articleConverter.getArticlePreviewFromObject(articles)).thenReturn(returnArticlePreviewDTOs);
        List<ArticlePreviewDTO> articlePreviewDTOs = articleService.getArticlePreviews();
        assertThat(articlePreviewDTOs).isNotNull();
    }

    @Test
    public void testGetArticlePreviews_returnObject() {
        List<ArticlePreviewDTO> returnArticlePreviewDTOs = new ArrayList<>();
        List<Article> articles = new ArrayList<>();
        when(articleRepository.findAll()).thenReturn(articles);
        when(articleConverter.getArticlePreviewFromObject(articles)).thenReturn(returnArticlePreviewDTOs);
        List<ArticlePreviewDTO> articlePreviewDTOs = articleService.getArticlePreviews();
        verify(articleRepository, times(1)).findAll();
        assertThat(articlePreviewDTOs).isEqualTo(returnArticlePreviewDTOs);
    }

    @Test
    public void testGetArticle_returnNotNull() {
        Long id = 1L;
        ArticleDTO returnArticleDTO = setupValidArticleDTO();
        Article article = setupValidArticle();
        when(articleRepository.findById(id)).thenReturn(article);
        when(articleConverter.getDTOFromObject(article)).thenReturn(returnArticleDTO);
        ArticleDTO articlePreviewDTOs = articleService.getArticle(id);
        assertThat(articlePreviewDTOs).isNotNull();
    }

    @Test
    public void testGetArticle_returnObject() {
        Long id = 1L;
        ArticleDTO returnArticleDTO = setupValidArticleDTO();
        Article article = setupValidArticle();
        when(articleRepository.findById(id)).thenReturn(article);
        when(articleConverter.getDTOFromObject(article)).thenReturn(returnArticleDTO);
        ArticleDTO articlePreviewDTOs = articleService.getArticle(id);
        verify(articleRepository, times(1)).findById(id);
        assertThat(articlePreviewDTOs).isEqualTo(returnArticleDTO);
    }

    private ArticleDTO setupArticleDTO(AddArticleDTO addArticleDTO) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(1L);
        articleDTO.setTitle(addArticleDTO.getTitle());
        articleDTO.setContent(addArticleDTO.getContent());
        return articleDTO;
    }

    private ArticleDTO setupValidArticleDTO() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(1L);
        articleDTO.setTitle(ARTICLE_TITLE_VALID);
        articleDTO.setContent(ARTICLE_CONTENT_VALID);
        return articleDTO;
    }

    private Article setupValidArticle() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle(ARTICLE_TITLE_VALID);
        article.setContent(ARTICLE_CONTENT_VALID);
        return article;
    }

    private AddArticleDTO setupValidAddArticle() {
        AddArticleDTO addArticleDTO = new AddArticleDTO();
        addArticleDTO.setTitle(ARTICLE_TITLE_VALID);
        addArticleDTO.setContent(ARTICLE_CONTENT_VALID);
        addArticleDTO.setUserId(1L);
        return addArticleDTO;
    }

}
