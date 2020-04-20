package ru.mail.dimaushenko.service.converter.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.constants.UserRoleEnum;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.repository.model.UserDetails;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.converter.UserDetailsConverter;
import ru.mail.dimaushenko.service.converter.UserRoleConverter;
import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.service.model.UserDTO;
import ru.mail.dimaushenko.service.model.UserDetailsDTO;
import ru.mail.dimaushenko.service.model.UserRoleEnumDTO;

@Component
public class UserConverterImpl implements UserConverter {

    private final UserDetailsConverter userDetailsConverter;
    private final UserRoleConverter userRoleConverter;
    private final PasswordEncoder passwordEncoder;

    public UserConverterImpl(
            UserDetailsConverter userDetailsConverter,
            UserRoleConverter userRoleConverter,
            PasswordEncoder passwordEncoder
    ) {
        this.userDetailsConverter = userDetailsConverter;
        this.userRoleConverter = userRoleConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO getDTOFromObject(User user) {
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            if (user.getUserDetails() != null) {
                UserDetailsDTO userDetailsDTO = userDetailsConverter.getDTOFromObject(user.getUserDetails());
                userDTO.setUserDetails(userDetailsDTO);
            }

            UserRoleEnumDTO role = userRoleConverter.getDTOFromObject(user.getRole());
            userDTO.setRole(role);
            return userDTO;
        }
        return null;
    }

    @Override
    public List<UserDTO> getDTOFromObject(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = getDTOFromObject(user);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public User getObjectFromDTO(UserDTO userDTO) {
        if (userDTO != null) {
            User user = new User();
            user.setId(userDTO.getId());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            if (userDTO.getUserDetails() != null) {
                UserDetails userDetails = userDetailsConverter.getObjectFromDTO(userDTO.getUserDetails());
                userDetails.setUser(user);
                user.setUserDetails(userDetails);
            }

            UserRoleEnum role = userRoleConverter.getObjectFromDTO(userDTO.getRole());
            user.setRole(role);
            return user;
        }
        return null;
    }

    @Override
    public User getObjectFromDTO(AddUserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserRoleEnum role = userRoleConverter.getObjectFromDTO(userDTO.getRole());
        user.setRole(role);

        UserDetails userDetails = new UserDetails();
        userDetails.setName(userDTO.getName());
        userDetails.setSurname(userDTO.getSurname());
        userDetails.setPatronymic(userDTO.getPatronymic());
        userDetails.setUser(user);
        user.setUserDetails(userDetails);
        return user;
    }

    @Override
    public List<User> getObjectFromDTO(List<UserDTO> userDTOs) {
        List<User> users = new ArrayList<>();
        for (UserDTO userDTO : userDTOs) {
            User user = getObjectFromDTO(userDTO);
            users.add(user);
        }
        return users;
    }

}
