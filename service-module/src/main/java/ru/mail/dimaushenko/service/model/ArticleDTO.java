package ru.mail.dimaushenko.service.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleDTO {

    private Long id;
    private UserDTO user;
    private String title;
    private String content;
    private Date date;
    private List<CommentArticleDTO> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CommentArticleDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentArticleDTO> comments) {
        this.comments = comments;
    }

}
