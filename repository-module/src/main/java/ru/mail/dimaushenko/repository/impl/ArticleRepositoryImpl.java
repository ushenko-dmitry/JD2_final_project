package ru.mail.dimaushenko.repository.impl;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.mail.dimaushenko.repository.ArticleRepository;
import ru.mail.dimaushenko.repository.constants.SortOrderEnum;
import ru.mail.dimaushenko.repository.model.Article;
import ru.mail.dimaushenko.repository.model.Pagination;

@Repository
public class ArticleRepositoryImpl extends GenericRepositoryImpl<Long, Article> implements ArticleRepository {

    @Override
    public List<Article> getArticlesSortByDate(Pagination pagination, SortOrderEnum sortOrderEnum) {
        String queryString = "FROM Article a ORDER BY a.date";
        switch (sortOrderEnum) {
            case ASC:
                queryString += " asc";
                break;
            case DESC:
                queryString += " desc";
                break;
        }
        Query query = entityManager.createQuery(queryString);
        query.setFirstResult(pagination.getStartElement());
        query.setMaxResults(pagination.getElementsPerPage());
        return query.getResultList();
    }

}
