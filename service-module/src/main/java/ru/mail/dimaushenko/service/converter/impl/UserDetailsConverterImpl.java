package ru.mail.dimaushenko.service.converter.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.UserDetails;
import ru.mail.dimaushenko.service.converter.UserDetailsConverter;
import ru.mail.dimaushenko.service.model.UserDetailsDTO;

@Component
public class UserDetailsConverterImpl implements UserDetailsConverter {

    @Override
    public UserDetailsDTO getDTOFromObject(UserDetails userDetails) {
        if (userDetails != null) {
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
            userDetailsDTO.setUserId(userDetails.getUserId());
            userDetailsDTO.setName(userDetails.getName());
            userDetailsDTO.setSurname(userDetails.getSurname());
            userDetailsDTO.setPatronymic(userDetails.getPatronymic());
            userDetailsDTO.setAddress(userDetails.getAddress());
            userDetailsDTO.setPhone(userDetails.getPhone());
            return userDetailsDTO;
        }
        return null;
    }

    @Override
    public List<UserDetailsDTO> getDTOFromObject(List<UserDetails> userDetailsList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDetails getObjectFromDTO(UserDetailsDTO userDetailsDTO) {
        if (userDetailsDTO != null) {
            UserDetails userDetails = new UserDetails();
            userDetails.setUserId(userDetailsDTO.getUserId());
            userDetails.setName(userDetailsDTO.getName());
            userDetails.setSurname(userDetailsDTO.getSurname());
            userDetails.setPatronymic(userDetailsDTO.getPatronymic());
            userDetails.setAddress(userDetailsDTO.getAddress());
            userDetails.setPhone(userDetailsDTO.getPhone());
            return userDetails;
        }
        return null;
    }

    @Override
    public List<UserDetails> getObjectFromDTO(List<UserDetailsDTO> userDetailsDTOList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
