package ru.mail.dimaushenko.service.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddArticleDTO {

    private Long userId;
    @Size(min = 1, message = "Title is empty")
    private String title;
    @NotNull
    private String content;
    private Date publicatedDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getPublicatedDate() {
        return publicatedDate;
    }

    public void setPublicatedDate(Date publicatedDate) {
        this.publicatedDate = publicatedDate;
    }

}
