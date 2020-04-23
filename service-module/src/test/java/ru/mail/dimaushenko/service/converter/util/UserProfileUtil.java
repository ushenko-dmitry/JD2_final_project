package ru.mail.dimaushenko.service.converter.util;

import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.repository.model.UserDetails;
import ru.mail.dimaushenko.service.model.UserProfileDTO;

public class UserProfileUtil {

    public static UserProfileDTO setupValidUserProfileDTO(User user) {
        UserProfileDTO profileDTO = new UserProfileDTO();
        profileDTO.setId(user.getId());
        UserDetails userDetails = user.getUserDetails();
        profileDTO.setName(userDetails.getName());
        profileDTO.setSurname(userDetails.getSurname());
        profileDTO.setAddress(userDetails.getAddress());
        profileDTO.setPhone(userDetails.getPhone());
        return profileDTO;
    }

    public static UserProfileDTO setupUserProfileWithNullUserDetailsDTO(User user) {
        UserProfileDTO profileDTO = new UserProfileDTO();
        profileDTO.setId(user.getId());
        profileDTO.setName(null);
        profileDTO.setSurname(null);
        profileDTO.setAddress(null);
        profileDTO.setPhone(null);
        return profileDTO;
    }

}
