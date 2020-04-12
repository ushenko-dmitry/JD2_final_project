package ru.mail.dimaushenko.service.impl;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import ru.mail.dimaushenko.repository.CommentRepository;
import ru.mail.dimaushenko.repository.UserRepository;
import ru.mail.dimaushenko.repository.constants.Sort;
import ru.mail.dimaushenko.repository.constants.UserRoleEnum;
import ru.mail.dimaushenko.repository.model.Comment;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.CommentService;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.converter.ConverterFacade;
import ru.mail.dimaushenko.service.converter.PaginationConverter;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.converter.UserRoleConverter;
import ru.mail.dimaushenko.service.converter.impl.PaginationConverterImpl;
import ru.mail.dimaushenko.service.converter.impl.UserConverterImpl;
import ru.mail.dimaushenko.service.converter.impl.UserDetailsConverterImpl;
import ru.mail.dimaushenko.service.converter.impl.UserRoleConverterImpl;
import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.UserDTO;
import ru.mail.dimaushenko.service.model.UserRoleEnumDTO;
import ru.mail.dimaushenko.service.utils.PasswordUtil;
import ru.mail.dimaushenko.service.utils.SentEmailUtil;
import ru.mail.dimaushenko.service.utils.impl.PaginationUtilImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private final UserConverter userConverter = new UserConverterImpl(new UserDetailsConverterImpl(), new UserRoleConverterImpl());
    private final UserRoleConverter userRoleConverter = new UserRoleConverterImpl();
    private final PaginationConverter paginationConverter = new PaginationConverterImpl(new PaginationUtilImpl());

    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CommentService commentService;
    @Mock
    private ConverterFacade converterFacade;
    @Mock
    private SentEmailUtil emailUtil;
    @Mock
    private PasswordUtil passwordUtil;

    private UserService userService;

    @Before
    public void setup() {
        userService = new UserServiceImpl(
                userRepository,
                commentRepository,
                commentService,
                converterFacade,
                emailUtil,
                passwordUtil
        );
    }

    @Test
    public void testLoadUserByUsername_resultIsNotNull() {
        String username = "username";
        doReturn(new User()).when(userRepository).getUserByEmail(username);
        doReturn(userConverter).when(converterFacade).getUserConverter();
        UserDTO userByEmail = userService.getUserByEmail(username);
        assertThat(userByEmail).isNotNull();
    }

    @Test
    public void testAddUser() {
        String password = "12345678";
        String email = "example@mail.com";
        AddUserDTO addUser = new AddUserDTO();
        addUser.setEmail(email);
        doReturn(password).when(passwordUtil).generatePassword();
        doReturn(userConverter).when(converterFacade).getUserConverter();
        userService.addUser(addUser);
        verify(passwordUtil, times(1)).generatePassword();
        verify(emailUtil, times(1)).sentMessage(email, "your password created", "Your password: " + password);
    }

    @Test
    public void testGetUserByEmail_resultIsNotNull() {
        String email = "email";
        doReturn(new User()).when(userRepository).getUserByEmail(email);
        doReturn(userConverter).when(converterFacade).getUserConverter();
        UserDTO userByEmail = userService.getUserByEmail(email);
        assertThat(userByEmail).isNotNull();
    }

    @Test
    public void testGetUserByEmail_resultIsObject() {
        String email = "email";
        doReturn(new User()).when(userRepository).getUserByEmail(email);
        doReturn(userConverter).when(converterFacade).getUserConverter();
        UserDTO userByEmail = userService.getUserByEmail(email);
        assertThat(userByEmail).isEqualTo(userByEmail);
    }

    @Test
    public void testGetUserByEmail_checkMethods() {
        String email = "email";
        doReturn(new User()).when(userRepository).getUserByEmail(email);
        doReturn(userConverter).when(converterFacade).getUserConverter();
        userService.getUserByEmail(email);
        verify(userRepository).getUserByEmail(email);
        verify(converterFacade).getUserConverter();
    }

    @Test
    public void testGetUserById_resultIsNotNull() {
        Long id = 1L;
        doReturn(new User()).when(userRepository).findById(id);
        doReturn(userConverter).when(converterFacade).getUserConverter();
        UserDTO userByEmail = userService.getUserById(id);
        assertThat(userByEmail).isNotNull();
    }

    @Test
    public void testGetUserById_resultIsObject() {
        Long id = 1L;
        doReturn(new User()).when(userRepository).findById(id);
        doReturn(userConverter).when(converterFacade).getUserConverter();
        UserDTO userByEmail = userService.getUserById(id);
        assertThat(userByEmail).isEqualTo(userByEmail);
    }

    @Test
    public void testGetUserById_checkMethods() {
        Long id = 1L;
        doReturn(new User()).when(userRepository).findById(id);
        doReturn(userConverter).when(converterFacade).getUserConverter();
        userService.getUserById(id);
        verify(userRepository).findById(id);
        verify(converterFacade).getUserConverter();
    }

    @Test
    public void testIsUsernameFound() {
        String username = "username";
        boolean returnValue = userService.isUsernameFound(username);
        verify(userRepository, times(1)).isUsernameFound(username);
        when(userService.isUsernameFound(username)).
                thenReturn(anyBoolean());
        assertThat(returnValue).isNotNull();
    }

    @Test
    public void testGetUsersSortByEmail() {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setCurrentPage(1);
        paginationDTO.setElementsPerPage(10);
        doReturn(paginationConverter).when(converterFacade).getPaginationConverter();
        Pagination pagination = paginationConverter.getObjectFromDTO(paginationDTO);
        doReturn(new ArrayList<User>()).when(userRepository).getUsersSortByEmail(pagination, Sort.ASC);
        doReturn(userConverter).when(converterFacade).getUserConverter();
        List<UserDTO> usersSortByEmail = userService.getUsersSortByEmail(paginationDTO);
        assertThat(usersSortByEmail).isNotNull();
        assertThat(usersSortByEmail).isEqualTo(anyList());
    }

    @Test
    public void testGetAmountUsers() {
        userService.getAmountUsers();
        verify(userRepository, times(1)).getAmountElements();
        when(userService.getAmountUsers()).
                thenReturn(anyInt());
    }

    @Test
    public void testGetAmountUsersByRole() {
        doReturn(userRoleConverter).when(converterFacade).getUserRoleConverter();
        userService.getAmountUsers(UserRoleEnumDTO.SALE_USER);
        verify(userRepository, times(1)).getAmountElements(UserRoleEnum.SALE_USER);
        when(userService.getAmountUsers()).
                thenReturn(anyInt());
    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;
        User user = new User();
        doReturn(user).when(userRepository).findById(id);
        doReturn(new ArrayList<Comment>()).when(commentRepository).getCommentsByUser(user);
        boolean result = userService.deleteUser(id);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testResetPassword() {
        Long id = 1L;
        String password = "12345678";
        User user = new User();
        doReturn(user).when(userRepository).findById(id);
        doReturn(password).when(passwordUtil).generatePassword();
        doReturn(true).when(emailUtil).sentMessage(user.getEmail(), "your password was reseted", "Your new password: " + password);
        boolean result = userService.resetPassword(id);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(true);
        verify(passwordUtil, times(1)).generatePassword();
        verify(emailUtil, times(1)).sentMessage(user.getEmail(), "your password was reseted", "Your new password: " + password);
    }

}
