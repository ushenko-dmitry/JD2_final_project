package ru.mail.dimaushenko.repository;

import ru.mail.dimaushenko.repository.model.User;

public interface UserRepository extends GenericRepository<Long, User>{

    User getEntityByEmail(String username);
    
    boolean isUsernameFound(String username);
    
}
