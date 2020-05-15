package ru.mail.dimaushenko.repository.impl;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.mail.dimaushenko.repository.CommentRepository;
import ru.mail.dimaushenko.repository.model.Comment;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.User;

@Repository
public class CommentRepositoryImpl extends GenericRepositoryImpl<Long, Comment> implements CommentRepository {

    @Override
    public List<Comment> getComments(Pagination pagination) {
        String queryString = "FROM Comment c";
        Query query = entityManager.createQuery(queryString);
        query.setFirstResult(pagination.getStartElement());
        query.setMaxResults(pagination.getElementsPerPage());
        return query.getResultList();
    }

    @Override
    public List<Comment> getCommentsByUser(User user) {
        String queryString = "FROM Comment c WHERE user_id=:user_id";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("user_id", user.getId());
        return query.getResultList();
    }

}
