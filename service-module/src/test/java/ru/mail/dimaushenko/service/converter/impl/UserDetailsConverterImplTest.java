package ru.mail.dimaushenko.service.converter.impl;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.mail.dimaushenko.repository.model.UserDetails;
import ru.mail.dimaushenko.service.converter.UserDetailsConverter;
import ru.mail.dimaushenko.service.model.UserDetailsDTO;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsConverterImplTest {

    private static final long ID = 1L;
    private static final String PHONE = "Phone";
    private static final String ADDRESS = "Address";
    private static final String PATRONYMIC = "Patronymic";
    private static final String SURNAME = "Surname";
    private static final String NAME = "Name";

    private UserDetailsConverter userDetailsConverter = new UserDetailsConverterImpl();

    @Before
    public void setUp() {
    }

    @Test
    public void testGetDTOFromObject_UserDetails_Input() {
        UserDetails userDetails = setupUserDetails();
        UserDetailsDTO returnUserDetailsDTO = setupUserDetailsDTO(userDetails);
        UserDetailsDTO userDetailsDTO = userDetailsConverter.getDTOFromObject(userDetails);
        assertThat(userDetailsDTO).isEqualTo(returnUserDetailsDTO);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetDTOFromObject_List() {
        List<UserDetails> userDetailses = new ArrayList<>();
        userDetailsConverter.getDTOFromObject(userDetailses);
    }

    @Test
    public void testGetObjectFromDTO_UserDetailsDTO_Input() {
        UserDetailsDTO userDetailsDTO = setupUserDetailsDTO();
        UserDetails returnUserDetails = setupUserDetails(userDetailsDTO);
        UserDetails userDetails = userDetailsConverter.getObjectFromDTO(userDetailsDTO);
        assertThat(userDetails).isEqualTo(returnUserDetails);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetObjectFromDTO_List() {
        List<UserDetailsDTO> userDetailsDTOs = new ArrayList<>();
        userDetailsConverter.getObjectFromDTO(userDetailsDTOs);
    }

    private UserDetails setupUserDetails() {
        UserDetails userDetails = new UserDetails();
        userDetails.setName(NAME);
        userDetails.setSurname(SURNAME);
        userDetails.setPatronymic(PATRONYMIC);
        userDetails.setAddress(ADDRESS);
        userDetails.setPhone(PHONE);
        userDetails.setUserId(ID);
        return userDetails;
    }

    private UserDetailsDTO setupUserDetailsDTO(UserDetails userDetails) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setName(userDetails.getName());
        userDetailsDTO.setSurname(userDetails.getSurname());
        userDetailsDTO.setPatronymic(userDetails.getPatronymic());
        userDetailsDTO.setAddress(userDetails.getAddress());
        userDetailsDTO.setPhone(userDetails.getPhone());
        userDetailsDTO.setUserId(userDetails.getUserId());
        return userDetailsDTO;
    }

    private UserDetails setupUserDetails(UserDetailsDTO userDetailsDTO) {
        UserDetails userDetails = new UserDetails();
        userDetails.setName(userDetailsDTO.getName());
        userDetails.setSurname(userDetailsDTO.getSurname());
        userDetails.setPatronymic(userDetailsDTO.getPatronymic());
        userDetails.setAddress(userDetailsDTO.getAddress());
        userDetails.setPhone(userDetailsDTO.getPhone());
        userDetails.setUserId(userDetailsDTO.getUserId());
        return userDetails;
    }

    private UserDetailsDTO setupUserDetailsDTO() {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setName(NAME);
        userDetailsDTO.setSurname(SURNAME);
        userDetailsDTO.setPatronymic(PATRONYMIC);
        userDetailsDTO.setAddress(ADDRESS);
        userDetailsDTO.setPhone(PHONE);
        userDetailsDTO.setUserId(ID);
        return userDetailsDTO;
    }

}
