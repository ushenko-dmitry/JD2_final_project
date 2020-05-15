package ru.mail.dimaushenko.service;

import java.util.List;
import ru.mail.dimaushenko.service.model.AddCommentDTO;
import ru.mail.dimaushenko.service.model.CommentDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;

public interface CommentService {

    void addComment(AddCommentDTO addCommentDTO);

    List<CommentDTO> getComments(PaginationDTO pagination);

    void deleteComment(Long id);

    Integer getAmountComments();

    CommentDTO getCommentById(Long commentId);

}
