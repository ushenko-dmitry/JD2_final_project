package ru.mail.dimaushenko.repository;

import java.util.List;
import ru.mail.dimaushenko.repository.constants.SortOrderEnum;
import ru.mail.dimaushenko.repository.model.Article;
import ru.mail.dimaushenko.repository.model.Pagination;

public interface ArticleRepository extends GenericRepository<Long, Article> {

    List<Article> getArticlesSortByDate(Pagination pagination, SortOrderEnum sortOrderEnum);

}
