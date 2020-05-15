package ru.mail.dimaushenko.service.converter;

import java.util.List;
import ru.mail.dimaushenko.repository.model.Comment;
import ru.mail.dimaushenko.service.model.CommentArticleDTO;
import ru.mail.dimaushenko.service.model.CommentDTO;

public interface CommentConverter extends GeneralConverter<CommentDTO, Comment> {

    CommentArticleDTO getCommentArticleFromObject(Comment comments);

    List<CommentArticleDTO> getCommentArticleFromObject(List<Comment> comments);

}
