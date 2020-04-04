package ru.mail.dimaushenko.repository.impl;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.mail.dimaushenko.repository.UserRepository;
import ru.mail.dimaushenko.repository.model.User;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {

    @Override
    public User getEntityByEmail(String email) {
        String queryString = "FROM " + entityClass.getSimpleName() + " u "
                + "WHERE u.email=:email";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    @Override
    public boolean isUsernameFound(String email) {
        String queryString = "SELECT count(id) FROM " + entityClass.getName() + " u WHERE u.email=:email";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("email", email);
        Long count = (Long) query.getSingleResult();
        return count.intValue() > 0;
    }

}
