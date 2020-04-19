package ru.mail.dimaushenko.webmodule.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.mail.dimaushenko.service.ArticleService;
import ru.mail.dimaushenko.service.model.ArticleDTO;
import ru.mail.dimaushenko.service.model.ArticlePreviewDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.ViewArticlesDTO;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_ARTICLES_PER_PAGE;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_CURRENT_PAGE;

@Controller
@RequestMapping("/articles")
@SessionAttributes(types = ViewArticlesDTO.class)
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
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
    public String getArticle(@PathVariable(name = "id") String idStr, Model model) {
        System.out.println(idStr);
        long id = Long.parseLong(idStr);
        ArticleDTO article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article";
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
            pagination.setCurrentPage(1);
        }
        return pagination;
    }

}
