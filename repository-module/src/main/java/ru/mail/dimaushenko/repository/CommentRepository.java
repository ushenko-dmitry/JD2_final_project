package ru.mail.dimaushenko.repository;

import java.util.List;
import ru.mail.dimaushenko.repository.model.Comment;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.User;

public interface CommentRepository extends GenericRepository<Long, Comment> {

    List<Comment> getComments(Pagination pagination);

    List<Comment> getCommentsByUser(User user);

}
