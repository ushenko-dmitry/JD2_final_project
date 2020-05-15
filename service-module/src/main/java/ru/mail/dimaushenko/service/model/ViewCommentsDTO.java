package ru.mail.dimaushenko.service.model;

import java.util.List;

public class ViewCommentsDTO {

    private PaginationDTO pagination;
    private List<CommentDTO> comments;

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

}
