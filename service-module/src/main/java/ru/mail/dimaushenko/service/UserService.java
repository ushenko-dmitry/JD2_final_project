package ru.mail.dimaushenko.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.UserDTO;
import ru.mail.dimaushenko.service.model.UserPasswordDTO;
import ru.mail.dimaushenko.service.model.UserProfileDTO;
import ru.mail.dimaushenko.service.model.UserRoleEnumDTO;

public interface UserService extends UserDetailsService {

    UserDTO addUser(AddUserDTO addUser);

    UserDTO getUserByEmail(String username);

    UserDTO getUserById(Long id);

    boolean isUsernameFound(String username);

    List<UserDTO> getUsersSortByEmail(PaginationDTO paginationDTO);

    Integer getAmountUsers();

    Integer getAmountUsers(UserRoleEnumDTO role);

    boolean deleteUser(Long id);

    boolean resetPassword(Long id);

    boolean updateUserRole(UserDTO user);

    boolean updateUserPassword(UserPasswordDTO userPasswordDTO);

    boolean updateUserDetails(UserProfileDTO user);

    UserProfileDTO getUserProfile(String username);

}
