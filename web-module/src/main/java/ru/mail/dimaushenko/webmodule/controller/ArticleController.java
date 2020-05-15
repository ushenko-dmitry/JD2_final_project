package ru.mail.dimaushenko.webmodule.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.mail.dimaushenko.service.ArticleService;
import ru.mail.dimaushenko.service.CommentService;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.model.AddArticleDTO;
import ru.mail.dimaushenko.service.model.AddCommentDTO;
import ru.mail.dimaushenko.service.model.AppUser;
import ru.mail.dimaushenko.service.model.ArticleDTO;
import ru.mail.dimaushenko.service.model.ArticlePreviewDTO;
import ru.mail.dimaushenko.service.model.CommentArticleDTO;
import ru.mail.dimaushenko.service.model.CommentDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.UserDTO;
import ru.mail.dimaushenko.service.model.ViewArticlesDTO;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_ARTICLES_PER_PAGE;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_CURRENT_PAGE;

@Controller
@RequestMapping("/articles")
@SessionAttributes(types = ViewArticlesDTO.class)
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;
    private final CommentService commentService;

    public ArticleController(
            ArticleService articleService,
            UserService userService,
            CommentService commentService
    ) {
        this.articleService = articleService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping
    public String getArticles(
            ViewArticlesDTO viewArticlesDTO,
            Model model
    ) {
        PaginationDTO pagination = setPagination(viewArticlesDTO.getPagination());
        viewArticlesDTO.setPagination(pagination);
        List<ArticlePreviewDTO> articles = articleService.getArticlePreviews(pagination);
        viewArticlesDTO.setArticles(articles);
        model.addAttribute("viewArticles", viewArticlesDTO);
        return "articles";
    }

    @GetMapping("/{id}")
    public String getArticle(
            @PathVariable(name = "id") Long id,
            Model model
    ) {
        ArticleDTO article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article";
    }

    @GetMapping("/add")
    public String addArticle(Model model) {
        model.addAttribute("newArticle", new AddArticleDTO());
        return "article_add";
    }

    @PostMapping("/add")
    public String addArticle(
            @Valid @ModelAttribute(name = "newArticle") AddArticleDTO addArticle,
            BindingResult bindingResult,
            Authentication authentication,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "article_add";
        }
        AppUser appUser = (AppUser) authentication.getPrincipal();
        UserDTO user = userService.getUserByEmail(appUser.getUsername());
        addArticle.setUserId(user.getId());
        articleService.addArticle(addArticle);
        return "redirect:/articles";
    }

    @PostMapping("/{id}/delete")
    public String deleteArticle(
            @PathVariable(name = "id") Long id,
            Authentication authentication
    ) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        ArticleDTO article = articleService.getArticle(id);
        if (article.getUser().getEmail().equals(appUser.getUsername())) {
            articleService.deleteArticle(id);
            return "redirect:/articles";
        }
        return "redirect:/articles?error";
    }

    @GetMapping("/{id}/edit")
    public String editArticle(
            @PathVariable(name = "id") Long id,
            Authentication authentication,
            Model model
    ) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        ArticleDTO article = articleService.getArticle(id);
        if (article.getUser().getEmail().equals(appUser.getUsername())) {
            model.addAttribute("article", article);
            return "article_edit";
        }
        return "redirect:/articles/" + id + "?error";
    }

    @PostMapping("/{id}/edit")
    public String editArticle(
            @PathVariable(name = "id") Long id,
            @Valid @ModelAttribute(name = "article") ArticleDTO articleDTO,
            BindingResult bindingResult,
            Authentication authentication,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "article_edit";
        }
        AppUser appUser = (AppUser) authentication.getPrincipal();
        ArticleDTO article = articleService.getArticle(id);
        if (article.getUser().getEmail().equals(appUser.getUsername())) {
            articleService.updateArticle(articleDTO);
            return "redirect:/articles/" + id;
        }
        model.addAttribute("article", article);
        return "article_edit";
    }

    @PostMapping("/{article_id}/comments/{comment_id}/delete")
    public String deleteComment(
            @PathVariable(name = "article_id") Long articleId,
            @PathVariable(name = "comment_id") Long commentId,
            Authentication authentication
    ) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        ArticleDTO article = articleService.getArticle(articleId);
        if (article.getUser().getEmail().equals(appUser.getUsername())) {
            CommentDTO comment = commentService.getCommentById(commentId);
            boolean isCommentContains = false;
            for (CommentArticleDTO commentArticle : article.getComments()) {
                if (commentArticle.getId() == comment.getId()) {
                    isCommentContains = true;
                    break;
                }
            }
            if (isCommentContains) {
                articleService.deleteComment(articleId, commentId);
            }
        }
        return "redirect:/articles/" + articleId + "/edit";
    }

    @GetMapping("/{id}/comments")
    public String showAddCommentPage(
            @PathVariable(name = "id") Long articleId,
            Model model
    ) {
        ArticleDTO article = articleService.getArticle(articleId);
        model.addAttribute("article", article);
        AddCommentDTO addCommentDTO = new AddCommentDTO();
        addCommentDTO.setArticleId(articleId);
        model.addAttribute("new_comment", addCommentDTO);
        return "comment_add";
    }

    @PostMapping("/{id}/comments")
    public String addComment(
            @PathVariable(name = "id") Long articleId,
            @Valid @ModelAttribute(name = "new_comment") AddCommentDTO addCommentDTO,
            BindingResult bindingResult,
            Model model,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            ArticleDTO article = articleService.getArticle(articleId);
            model.addAttribute("article", article);
            return "comment_add";
        }
        AppUser appUser = (AppUser) authentication.getPrincipal();
        UserDTO user = userService.getUserByEmail(appUser.getUsername());
        addCommentDTO.setUserId(user.getId());
        addCommentDTO.setArticleId(articleId);
        commentService.addComment(addCommentDTO);
        return "redirect:/articles/" + articleId;
    }

    private PaginationDTO setPagination(PaginationDTO pagination) {
        if (pagination == null) {
            pagination = new PaginationDTO();
        }
        if (pagination.getCurrentPage() == null) {
            pagination.setCurrentPage(DEFAULT_CURRENT_PAGE);
        }
        if (pagination.getElementsPerPage() == null) {
            pagination.setElementsPerPage(DEFAULT_ARTICLES_PER_PAGE);
        }
        Integer amountArticles = articleService.getAmountArticles();
        Integer amountPages = amountArticles / pagination.getElementsPerPage();
        if (amountArticles % pagination.getElementsPerPage() > 0) {
            amountPages++;
        }
        if (amountPages == 0) {
            amountPages++;
        }
        pagination.setPageNumbers(new ArrayList<>());
        for (int i = 1; i <= amountPages; i++) {
            pagination.getPageNumbers().add(i);
        }
        if (pagination.getPageNumbers().size() < pagination.getCurrentPage()) {
            pagination.setCurrentPage(DEFAULT_CURRENT_PAGE);
        }
        return pagination;
    }

}
