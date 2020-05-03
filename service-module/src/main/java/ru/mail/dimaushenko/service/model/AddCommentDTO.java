package ru.mail.dimaushenko.service.model;

import javax.validation.constraints.Size;

public class AddCommentDTO {

    private Long userId;
    private Long articleId;
    @Size(min = 1)
    private String comment;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
