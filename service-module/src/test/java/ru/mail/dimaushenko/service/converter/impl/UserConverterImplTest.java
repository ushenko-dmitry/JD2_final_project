package ru.mail.dimaushenko.service.converter.impl;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.repository.model.UserDetails;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.converter.UserDetailsConverter;
import ru.mail.dimaushenko.service.converter.UserRoleConverter;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_DTO_ROLE;
import static ru.mail.dimaushenko.service.converter.impl.util.Constants.USER_ROLE;
import static ru.mail.dimaushenko.service.converter.impl.util.UserDetailsUtil.getUserDetails;
import static ru.mail.dimaushenko.service.converter.impl.util.UserDetailsUtil.getUserDetailsDTO;
import static ru.mail.dimaushenko.service.converter.impl.util.UserUtil.getUserDTOWithNullUserDetails;
import static ru.mail.dimaushenko.service.converter.impl.util.UserUtil.getUserWithNullUserDetails;
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
        UserDetailsDTO userDetailsDTO = getUserDetailsDTO(user.getUserDetails());
        when(userDetailsConverter.getDTOFromObject(user.getUserDetails())).thenReturn(userDetailsDTO);
        when(userRoleConverter.getDTOFromObject(user.getRole())).thenReturn(USER_DTO_ROLE);
        UserDTO userDTO = userConverter.getDTOFromObject(user);
        assertThat(userDTO).isEqualTo(returnUserDTO);
    }

    @Test
    public void testGetDTOFromObject_UserWithNullUserDetails_Input() {
        User user = getUserWithNullUserDetails();
        UserDTO returnUserDTO = getUserDTOWithNullUserDetails(user);
        when(userRoleConverter.getDTOFromObject(user.getRole())).thenReturn(USER_DTO_ROLE);
        UserDTO userDTO = userConverter.getDTOFromObject(user);
        assertThat(userDTO).isEqualTo(returnUserDTO);
    }

    @Test
    public void testGetDTOFromObject_Null_Input() {
        User user = null;
        UserDTO userDTO = userConverter.getDTOFromObject(user);
        assertThat(userDTO).isNull();
    }

    @Test
    public void testGetDTOFromObject_EmptyList_Input() {
        List<User> users = new ArrayList<>();
        List<UserDTO> returnUserDTOs = new ArrayList<>();
        List<UserDTO> userDTOs = userConverter.getDTOFromObject(users);
        assertThat(userDTOs).isEqualTo(returnUserDTOs);
        assertThat(userDTOs).isEmpty();
    }

    @Test
    public void testGetDTOFromObject_ValidList_Input() {
        List<User> users = new ArrayList<>();
        User user = getValidUser();
        users.add(user);

        List<UserDTO> returnUserDTOs = new ArrayList<>();
        UserDTO userDTO = getValidUserDTO(user);
        returnUserDTOs.add(userDTO);

        List<UserDTO> userDTOs = userConverter.getDTOFromObject(users);
        assertThat(userDTOs).isNotEmpty();
    }

    @Test
    public void testGetObjectFromDTO_ValidUserDTO_Input() {
        UserDTO userDTO = getValidUserDTO();
        User returnUser = getValidUser(userDTO);
        UserDetails userDetails = getUserDetails(userDTO.getUserDetails());
        when(userDetailsConverter.getObjectFromDTO(userDTO.getUserDetails())).thenReturn(userDetails);
        when(userRoleConverter.getObjectFromDTO(userDTO.getRole())).thenReturn(USER_ROLE);
        User user = userConverter.getObjectFromDTO(userDTO);
        assertThat(user).isEqualTo(returnUser);
    }

    @Test
    public void testGetObjectFromDTO_UserDTOWithNullUserDetailsDTO_Input() {
        UserDTO userDTO = getUserDTOWithNullUserDetails();
        User returnUser = getUserWithNullUserDetails();
        when(userRoleConverter.getObjectFromDTO(userDTO.getRole())).thenReturn(USER_ROLE);
        User user = userConverter.getObjectFromDTO(userDTO);
        assertThat(user).isEqualTo(returnUser);
    }

    @Test
    public void testGetObjectFromDTO_Null_Input() {
        UserDTO userDTO = null;
        User user = userConverter.getObjectFromDTO(userDTO);
        assertThat(user).isNull();
    }

    @Test
    public void testGetObjectFromDTO_AddUserDTO() {
    }

    @Test
    public void testGetObjectFromDTO_List() {
    }

}
