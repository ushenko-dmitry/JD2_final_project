package ru.mail.dimaushenko.service.converter.impl.util;

import ru.mail.dimaushenko.repository.constants.UserRoleEnum;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.repository.model.UserDetails;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_DTO_ROLE;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_EMAIL;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_ID;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_PASSWORD;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_ROLE;
import static ru.mail.dimaushenko.service.converter.impl.util.UserDetailsUtil.getValidUserDetails;
import static ru.mail.dimaushenko.service.converter.impl.util.UserDetailsUtil.getValidUserDetailsDTO;
import ru.mail.dimaushenko.service.model.UserDTO;
import ru.mail.dimaushenko.service.model.UserDetailsDTO;

public class UserUtil {

    public static User getValidUser() {
        User user = new User();
        user.setId(USER_ID);
        user.setEmail(USER_EMAIL);
        user.setPassword(USER_PASSWORD);
        user.setRole(USER_ROLE);
        UserDetails userDetails = getValidUserDetails();
        user.setUserDetails(userDetails);
        return user;
    }

    public static User getValidUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(USER_ROLE);
        UserDetails userDetails = getValidUserDetails();
        user.setUserDetails(userDetails);
        return user;
    }

    public static User getUserWithNullUserDetails() {
        User user = new User();
        user.setId(USER_ID);
        user.setEmail(USER_EMAIL);
        user.setPassword(USER_PASSWORD);
        user.setRole(UserRoleEnum.SALE_USER);
        UserDetails userDetails = null;
        user.setUserDetails(userDetails);
        return user;
    }

    public static UserDTO getValidUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(USER_ID);
        userDTO.setEmail(USER_EMAIL);
        userDTO.setPassword(USER_PASSWORD);
        userDTO.setRole(USER_DTO_ROLE);
        UserDetailsDTO userDetailsDTO = getValidUserDetailsDTO();
        userDTO.setUserDetails(userDetailsDTO);
        return userDTO;
    }

    public static UserDTO getValidUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(USER_DTO_ROLE);
        UserDetailsDTO userDetailsDTO = getValidUserDetailsDTO();
        userDTO.setUserDetails(userDetailsDTO);
        return userDTO;
    }

    public static UserDTO getUserDTOWithNullUserDetails(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(USER_DTO_ROLE);
        UserDetailsDTO userDetailsDTO = null;
        userDTO.setUserDetails(userDetailsDTO);
        return userDTO;
    }

    public static UserDTO getUserDTOWithNullUserDetails() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(USER_ID);
        userDTO.setEmail(USER_EMAIL);
        userDTO.setPassword(USER_PASSWORD);
        userDTO.setRole(USER_DTO_ROLE);
        UserDetailsDTO userDetailsDTO = null;
        userDTO.setUserDetails(userDetailsDTO);
        return userDTO;
    }

}
