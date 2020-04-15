package ru.mail.dimaushenko.repository.impl;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.mail.dimaushenko.repository.UserRepository;
import ru.mail.dimaushenko.repository.constants.Sort;
import ru.mail.dimaushenko.repository.constants.UserRoleEnum;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.User;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {

    @Override
    public User getUserByEmail(String email) {
        String queryString = "FROM " + entityClass.getSimpleName() + " u "
                + "WHERE u.email=:email";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> getUsersSortByEmail(Pagination pagination, Sort sort) {
        String queryString = "FROM User u ORDER BY u.email";
        switch(sort){
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
    public boolean isUsernameFound(String email) {
        String queryString = "SELECT count(id) FROM " + entityClass.getName() + " u WHERE u.email=:email";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("email", email);
        Long count = (Long) query.getSingleResult();
        return count.intValue() > 0;
    }

    @Override
    public Integer getAmountElements(UserRoleEnum role) {
        String queryString = "SELECT count(id) FROM " + entityClass.getName() + " u WHERE u.role = :role";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("role", role);
        try {
            Long count = (Long) query.getSingleResult();
            return count.intValue();
        } catch (NoResultException noResultException) {
            return 0;
        }

    }

}
