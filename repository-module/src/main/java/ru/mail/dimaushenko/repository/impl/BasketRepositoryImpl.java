package ru.mail.dimaushenko.repository.impl;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.mail.dimaushenko.repository.BasketRepository;
import ru.mail.dimaushenko.repository.constants.SortOrderEnum;
import ru.mail.dimaushenko.repository.model.Basket;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.User;

@Repository
public class BasketRepositoryImpl extends GenericRepositoryImpl<Long, Basket> implements BasketRepository {

    @Override
    public List<Basket> getBasketsOrderedByDate(Pagination pagination, SortOrderEnum sortOrderEnum) {
        String queryString = "FROM Basket b ORDER BY b.creationDate";
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

    @Override
    public List<Basket> getBasketsForCustomerOrderedByDate(User user, Pagination pagination, SortOrderEnum sortOrderEnum) {
        String queryString = "FROM Basket b "
                + "WHERE b.user=:user "
                + "ORDER BY b.creationDate ";
        switch (sortOrderEnum) {
            case ASC:
                queryString += " asc";
                break;
            case DESC:
                queryString += " desc";
                break;
        }
        Query query = entityManager.createQuery(queryString);
        query.setParameter("user", user);
        query.setFirstResult(pagination.getStartElement());
        query.setMaxResults(pagination.getElementsPerPage());
        return query.getResultList();
    }

    @Override
    public List<Basket> getBasketsForSalerOrderedByDate(User user, Pagination pagination, SortOrderEnum sortOrderEnum) {
        String queryString = "FROM Basket b "
                + "WHERE b.orderedItem.user=:user "
                + "ORDER BY b.creationDate ";
        switch (sortOrderEnum) {
            case ASC:
                queryString += " asc";
                break;
            case DESC:
                queryString += " desc";
                break;
        }
        Query query = entityManager.createQuery(queryString);
        query.setParameter("user", user);
        query.setFirstResult(pagination.getStartElement());
        query.setMaxResults(pagination.getElementsPerPage());
        return query.getResultList();
    }

    @Override
    public Integer getAmountBasketsForCustomer(User user) {
        String queryString = "SELECT count(id) FROM Basket b WHERE b.user=:user";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("user", user);
        try {
            Long count = (Long) query.getSingleResult();
            return count.intValue();
        } catch (NoResultException noResultException) {
            return 0;
        }
    }

    @Override
    public Integer getAmountBasketsForSaler(User user) {
        String queryString = "SELECT count(id) FROM Basket b WHERE b.orderedItem.user=:user";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("user", user);
        try {
            Long count = (Long) query.getSingleResult();
            return count.intValue();
        } catch (NoResultException noResultException) {
            return 0;
        }
    }

}
