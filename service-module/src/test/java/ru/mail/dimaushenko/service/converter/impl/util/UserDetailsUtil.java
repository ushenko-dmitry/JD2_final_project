package ru.mail.dimaushenko.service.converter.impl.util;

import ru.mail.dimaushenko.repository.model.UserDetails;
import ru.mail.dimaushenko.service.model.UserDetailsDTO;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_ID;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_DETAILS_PHONE;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_DETAILS_ADDRESS;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_DETAILS_PATRONYMIC;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_DETAILS_SURNAME;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_DETAILS_NAME;

public class UserDetailsUtil {

    public static UserDetailsDTO getUserDetailsDTO(UserDetails userDetails) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setName(userDetails.getName());
        userDetailsDTO.setSurname(userDetails.getSurname());
        userDetailsDTO.setPatronymic(userDetails.getPatronymic());
        userDetailsDTO.setAddress(userDetails.getAddress());
        userDetailsDTO.setPhone(userDetails.getPhone());
        userDetailsDTO.setUserId(userDetails.getUserId());
        return userDetailsDTO;
    }

    public static UserDetailsDTO getValidUserDetailsDTO() {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setName(USER_DETAILS_NAME);
        userDetailsDTO.setSurname(USER_DETAILS_SURNAME);
        userDetailsDTO.setPatronymic(USER_DETAILS_PATRONYMIC);
        userDetailsDTO.setAddress(USER_DETAILS_ADDRESS);
        userDetailsDTO.setPhone(USER_DETAILS_PHONE);
        userDetailsDTO.setUserId(USER_ID);
        return userDetailsDTO;
    }

    public static UserDetails getValidUserDetails() {
        UserDetails userDetails = new UserDetails();
        userDetails.setName(USER_DETAILS_NAME);
        userDetails.setSurname(USER_DETAILS_SURNAME);
        userDetails.setPatronymic(USER_DETAILS_PATRONYMIC);
        userDetails.setAddress(USER_DETAILS_ADDRESS);
        userDetails.setPhone(USER_DETAILS_PHONE);
        userDetails.setUserId(USER_ID);
        return userDetails;
    }

    public static UserDetails getUserDetails(UserDetailsDTO userDetailsDTO) {
        UserDetails userDetails = new UserDetails();
        userDetails.setName(userDetailsDTO.getName());
        userDetails.setSurname(userDetailsDTO.getSurname());
        userDetails.setPatronymic(userDetailsDTO.getPatronymic());
        userDetails.setAddress(userDetailsDTO.getAddress());
        userDetails.setPhone(userDetailsDTO.getPhone());
        userDetails.setUserId(userDetailsDTO.getUserId());
        return userDetails;
    }

}
