package ru.mail.dimaushenko.repository;

import java.util.List;
import ru.mail.dimaushenko.repository.constants.Sort;
import ru.mail.dimaushenko.repository.constants.UserRoleEnum;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.User;

public interface UserRepository extends GenericRepository<Long, User>{

    User getUserByEmail(String username);
    
    List<User> getUsersSortByEmail(Pagination pagination, Sort sort);
    
    boolean isUsernameFound(String username);

    Integer getAmountElements(UserRoleEnum role);
    
}
