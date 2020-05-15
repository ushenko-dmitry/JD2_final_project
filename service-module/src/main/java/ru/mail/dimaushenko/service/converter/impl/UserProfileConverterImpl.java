package ru.mail.dimaushenko.service.converter.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.repository.model.UserDetails;
import ru.mail.dimaushenko.service.converter.UserDetailsConverter;
import ru.mail.dimaushenko.service.converter.UserProfileConverter;
import ru.mail.dimaushenko.service.model.UserDetailsDTO;
import ru.mail.dimaushenko.service.model.UserProfileDTO;

@Component
public class UserProfileConverterImpl implements UserProfileConverter {

    private final UserDetailsConverter userDetailsConverter;

    public UserProfileConverterImpl(UserDetailsConverter userDetailsConverter) {
        this.userDetailsConverter = userDetailsConverter;
    }

    @Override
    public UserProfileDTO getDTOFromObject(User user) {
        if (user != null) {
            UserProfileDTO profileDTO = new UserProfileDTO();
            profileDTO.setId(user.getId());
            UserDetails userDetails = user.getUserDetails();
            UserDetailsDTO userDetailsDTO = userDetailsConverter.getDTOFromObject(userDetails);
            if (userDetailsDTO != null) {
                profileDTO.setName(userDetailsDTO.getName());
                profileDTO.setSurname(userDetailsDTO.getSurname());
                profileDTO.setAddress(userDetailsDTO.getAddress());
                profileDTO.setPhone(userDetailsDTO.getPhone());
            }
            return profileDTO;
        }
        return null;
    }

    @Override
    public List<UserProfileDTO> getDTOFromObject(List<User> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getObjectFromDTO(UserProfileDTO modelDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getObjectFromDTO(List<UserProfileDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
