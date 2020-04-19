package ru.mail.dimaushenko.service.converter.impl;

import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import ru.mail.dimaushenko.repository.constants.UserRoleEnum;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.repository.model.UserDetails;
import ru.mail.dimaushenko.service.converter.UserDetailsConverter;
import ru.mail.dimaushenko.service.converter.UserProfileConverter;
import ru.mail.dimaushenko.service.model.UserDetailsDTO;
import ru.mail.dimaushenko.service.model.UserProfileDTO;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileConverterImplTest {

    private static final long ID = 1L;
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";

    private static final String PHONE = "Phone";
    private static final String ADDRESS = "Address";
    private static final String PATRONYMIC = "Patronymic";
    private static final String SURNAME = "Surname";
    private static final String NAME = "Name";

    @Mock
    private UserDetailsConverter userDetailsConverter;

    private UserProfileConverter userProfileConverter;

    @Before
    public void setup() {
        userProfileConverter = new UserProfileConverterImpl(userDetailsConverter);
    }

    @Test
    public void testGetDTOFromObject_User_Input() {
        User user = setupValidUser();
        UserProfileDTO returnUserProfileDTO = setupValidUserProfileDTO(user);
        UserDetailsDTO userDetailsDTO = setupUserDetails(user.getUserDetails());
        when(userDetailsConverter.getDTOFromObject(user.getUserDetails())).thenReturn(userDetailsDTO);
        UserProfileDTO userProfileDTO = userProfileConverter.getDTOFromObject(user);
        assertThat(userProfileDTO).isEqualTo(returnUserProfileDTO);
    }

    @Test
    public void testGetDTOFromObject_NULL_Input() {
        User user = null;
        UserProfileDTO userProfileDTO = userProfileConverter.getDTOFromObject(user);
        assertThat(userProfileDTO).isNull();
    }

    @Test
    public void testGetDTOFromObject_UserWithNullUserDetails_Input() {
        User user = setupUserWithNullUserDetails();
        UserProfileDTO returnUserProfileDTO = setupUserProfileWithNullUserDetailsDTO(user);
        UserDetailsDTO userDetailsDTO = null;
        when(userDetailsConverter.getDTOFromObject(user.getUserDetails())).thenReturn(userDetailsDTO);
        UserProfileDTO userProfileDTO = userProfileConverter.getDTOFromObject(user);
        assertThat(userProfileDTO).isEqualTo(returnUserProfileDTO);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetDTOFromObject_List() {
        ArrayList<User> users = new ArrayList<>();
        userProfileConverter.getDTOFromObject(users);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetObjectFromDTO_UserProfileDTO() {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileConverter.getObjectFromDTO(userProfileDTO);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetObjectFromDTO_List() {
        ArrayList<UserProfileDTO> userProfileDTOs = new ArrayList<>();
        userProfileConverter.getObjectFromDTO(userProfileDTOs);
    }

    private User setupValidUser() {
        User user = new User();
        user.setId(ID);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(UserRoleEnum.SALE_USER);
        UserDetails userDetails = new UserDetails();
        userDetails.setName(NAME);
        userDetails.setSurname(SURNAME);
        userDetails.setPatronymic(PATRONYMIC);
        userDetails.setAddress(ADDRESS);
        userDetails.setPhone(PHONE);
        userDetails.setUserId(ID);
        user.setUserDetails(userDetails);
        return user;
    }

    private UserDetailsDTO setupUserDetails(UserDetails userDetails) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setName(userDetails.getName());
        userDetailsDTO.setSurname(userDetails.getSurname());
        userDetailsDTO.setPatronymic(userDetails.getPatronymic());
        userDetailsDTO.setAddress(userDetails.getAddress());
        userDetailsDTO.setPhone(userDetails.getPhone());
        userDetailsDTO.setUserId(userDetails.getUserId());
        return userDetailsDTO;
    }

    private UserProfileDTO setupValidUserProfileDTO(User user) {
        UserProfileDTO profileDTO = new UserProfileDTO();
        profileDTO.setId(user.getId());
        UserDetails userDetails = user.getUserDetails();
        profileDTO.setName(userDetails.getName());
        profileDTO.setSurname(userDetails.getSurname());
        profileDTO.setAddress(userDetails.getAddress());
        profileDTO.setPhone(userDetails.getPhone());
        return profileDTO;
    }

    private User setupUserWithNullUserDetails() {
        User user = new User();
        user.setId(ID);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setRole(UserRoleEnum.SALE_USER);
        UserDetails userDetails = null;
        user.setUserDetails(userDetails);
        return user;
    }

    private UserProfileDTO setupUserProfileWithNullUserDetailsDTO(User user) {
        UserProfileDTO profileDTO = new UserProfileDTO();
        profileDTO.setId(user.getId());
        profileDTO.setName(null);
        profileDTO.setSurname(null);
        profileDTO.setAddress(null);
        profileDTO.setPhone(null);
        return profileDTO;
    }

}
