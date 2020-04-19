package ru.mail.dimaushenko.service.model;

import java.util.List;

public class ViewArticlesDTO {

    private PaginationDTO pagination;
    private List<ArticlePreviewDTO> articles;

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

    public List<ArticlePreviewDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlePreviewDTO> articles) {
        this.articles = articles;
    }

}
