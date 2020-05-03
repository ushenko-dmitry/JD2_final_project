package ru.mail.dimaushenko.service.converter;

import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.converter.UserDetailsConverter;
import ru.mail.dimaushenko.service.converter.UserProfileConverter;
import ru.mail.dimaushenko.service.converter.impl.UserProfileConverterImpl;
import static ru.mail.dimaushenko.service.converter.util.UserProfileUtil.setupUserProfileWithNullUserDetailsDTO;
import static ru.mail.dimaushenko.service.converter.util.UserProfileUtil.setupValidUserProfileDTO;
import ru.mail.dimaushenko.service.model.UserDetailsDTO;
import ru.mail.dimaushenko.service.model.UserProfileDTO;
import static ru.mail.dimaushenko.service.converter.util.UserUtil.getValidUser;
import static ru.mail.dimaushenko.service.converter.util.UserUtil.getUserWithNullUserDetails;
import static ru.mail.dimaushenko.service.converter.util.UserDetailsUtil.getValidUserDetailsDTO;
import static ru.mail.dimaushenko.service.converter.util.UserDetailsUtil.getUserDetailsDTO;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileConverterImplTest {

    @Mock
    private UserDetailsConverter userDetailsConverter;

    private UserProfileConverter userProfileConverter;

    @Before
    public void setup() {
        userProfileConverter = new UserProfileConverterImpl(userDetailsConverter);
    }

    @Test
    public void testGetDTOFromObject_User_Input() {
        User user = getValidUser();
        UserProfileDTO returnUserProfileDTO = setupValidUserProfileDTO(user);
        UserDetailsDTO userDetailsDTO = getUserDetailsDTO(user.getUserDetails());
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
        User user = getUserWithNullUserDetails();
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

}
