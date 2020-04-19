package ru.mail.dimaushenko.service.converter.impl;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.converter.UserDetailsConverter;
import ru.mail.dimaushenko.service.converter.UserRoleConverter;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_DTO_ROLE;
import static ru.mail.dimaushenko.service.converter.impl.util.UserDetailsUtil.getUserDetailsDTO;
import static ru.mail.dimaushenko.service.converter.impl.util.UserUtil.getValidUser;
import static ru.mail.dimaushenko.service.converter.impl.util.UserUtil.getValidUserDTO;
import ru.mail.dimaushenko.service.model.UserDTO;
import ru.mail.dimaushenko.service.model.UserDetailsDTO;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterImplTest {

    @Mock
    private UserDetailsConverter userDetailsConverter;
    @Mock
    private UserRoleConverter userRoleConverter;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserConverter userConverter;

    @Before
    public void setUp() {
        userConverter = new UserConverterImpl(userDetailsConverter, userRoleConverter, passwordEncoder);
    }

    @Test
    public void testGetDTOFromObject_ValidUser_Input() {
        User user = getValidUser();
        UserDTO returnUserDTO = getValidUserDTO(user);
        UserDetailsDTO userDetails = getUserDetailsDTO(user.getUserDetails());
        when(userDetailsConverter.getDTOFromObject(user.getUserDetails())).thenReturn(userDetails);
        when(userRoleConverter.getDTOFromObject(user.getRole())).thenReturn(USER_DTO_ROLE);
        UserDTO userDTO = userConverter.getDTOFromObject(user);
        assertThat(userDTO).isEqualTo(returnUserDTO);
    }

    @Test
    public void testGetDTOFromObject_List() {
    }

    @Test
    public void testGetObjectFromDTO_UserDTO() {
    }

    @Test
    public void testGetObjectFromDTO_AddUserDTO() {
    }

    @Test
    public void testGetObjectFromDTO_List() {
    }

}
