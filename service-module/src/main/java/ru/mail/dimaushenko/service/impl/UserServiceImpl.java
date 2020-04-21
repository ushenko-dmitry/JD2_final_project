package ru.mail.dimaushenko.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.CommentRepository;
import ru.mail.dimaushenko.repository.UserRepository;
import static ru.mail.dimaushenko.repository.constants.SortOrderEnum.ASC;
import ru.mail.dimaushenko.repository.constants.UserRoleEnum;
import static ru.mail.dimaushenko.repository.constants.UserRoleEnum.ADMINISTRATOR;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.CommentService;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.converter.ConverterFacade;
import ru.mail.dimaushenko.service.converter.PaginationConverter;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.converter.UserProfileConverter;
import ru.mail.dimaushenko.service.converter.UserRoleConverter;
import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.service.model.AppUser;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.UserDTO;
import ru.mail.dimaushenko.service.model.UserPasswordDTO;
import ru.mail.dimaushenko.service.model.UserProfileDTO;
import ru.mail.dimaushenko.service.model.UserRoleEnumDTO;
import ru.mail.dimaushenko.service.utils.PasswordUtil;
import ru.mail.dimaushenko.service.utils.SentEmailUtil;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final ConverterFacade converterFacade;
    private final SentEmailUtil emailUtil;
    private final PasswordUtil passwordUtil;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            CommentRepository commentRepository,
            CommentService commentService,
            ConverterFacade converterFacade,
            SentEmailUtil emailUtil,
            PasswordUtil passwordUtil,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
        this.converterFacade = converterFacade;
        this.emailUtil = emailUtil;
        this.passwordUtil = passwordUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String emial) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(emial);
        UserConverter userConverter = converterFacade.getUserConverter();
        UserDTO userDTO = userConverter.getDTOFromObject(user);

        if (userDTO == null) {
            throw new UsernameNotFoundException("User is not found");
        }
        AppUser appUser = new AppUser(userDTO);
        return appUser;
    }

    @Override
    public UserDTO addUser(AddUserDTO addUser) {
        String password = passwordUtil.generatePassword();
        addUser.setPassword(password);
        UserConverter userConverter = converterFacade.getUserConverter();
        User user = userConverter.getObjectFromDTO(addUser);
        userRepository.persist(user);
        user.getUserDetails().setUserId(user.getId());
        String to = user.getEmail();
        String subject = "your password created";
        String message = "Your password: " + password;
        emailUtil.sentMessage(to, subject, message);
        return userConverter.getDTOFromObject(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        UserConverter userConverter = converterFacade.getUserConverter();
        UserDTO userDTO = userConverter.getDTOFromObject(user);
        return userDTO;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id);
        UserConverter userConverter = converterFacade.getUserConverter();
        UserDTO userDTO = userConverter.getDTOFromObject(user);
        return userDTO;
    }

    @Override
    public boolean isUsernameFound(String username) {
        return userRepository.isUsernameFound(username);
    }

    @Override
    public List<UserDTO> getUsersSortByEmail(PaginationDTO paginationDTO) {
        PaginationConverter paginationConverter = converterFacade.getPaginationConverter();
        Pagination pagination = paginationConverter.getObjectFromDTO(paginationDTO);
        List<User> users = userRepository.getUsersSortByEmail(pagination, ASC);
        UserConverter userConverter = converterFacade.getUserConverter();
        List<UserDTO> userDTOs = userConverter.getDTOFromObject(users);
        return userDTOs;
    }

    @Override
    public UserProfileDTO getUserProfile(String username) {
        User user = userRepository.getUserByEmail(username);
        UserProfileConverter userProfileConverter = converterFacade.getUserProfileConverter();
        UserProfileDTO userProfileDTO = userProfileConverter.getDTOFromObject(user);
        return userProfileDTO;
    }

    @Override
    public Integer getAmountUsers() {
        return userRepository.getAmountElements();
    }

    @Override
    public Integer getAmountUsers(UserRoleEnumDTO roleDTO) {
        UserRoleConverter userRoleConverter = converterFacade.getUserRoleConverter();
        UserRoleEnum role = userRoleConverter.getObjectFromDTO(roleDTO);
        return userRepository.getAmountElements(role);
    }

    @Override
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id);
        if (checkAmountAdministratorUsers(user)) {
            return false;
        }
        userRepository.remove(user);
        return true;
    }

    @Override
    public boolean resetPassword(Long id) {
        User user = userRepository.findById(id);
        String passwordBuckup = user.getPassword();
        String password = passwordUtil.generatePassword();
        user.setPassword(passwordEncoder.encode(password));
        String to = user.getEmail();
        String subject = "your password was reseted";
        String message = "Your new password: " + password;
        boolean isSentMessage = emailUtil.sentMessage(to, subject, message);
        if (!isSentMessage) {
            user.setPassword(passwordBuckup);
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUserRole(UserDTO updated_userDTO) {
        User user = userRepository.findById(updated_userDTO.getId());
        if (checkAmountAdministratorUsers(user)) {
            return false;
        }
        UserConverter userConverter = converterFacade.getUserConverter();
        User updated_user = userConverter.getObjectFromDTO(updated_userDTO);
        user.setRole(updated_user.getRole());
        return true;
    }

    @Override
    public boolean updateUserPassword(UserPasswordDTO userPasswordDTO) {
        User user = userRepository.findById(userPasswordDTO.getId());
        String password = passwordEncoder.encode(userPasswordDTO.getPassword());
        System.out.println(password);
        user.setPassword(password);
        return true;
    }

    @Override
    public boolean updateUserDetails(UserProfileDTO userProfileDTO) {
        User user = userRepository.findById(userProfileDTO.getId());
        updateUser(user, userProfileDTO);
        return true;
    }

    private void updateUser(User user, UserProfileDTO userProfileDTO) {
        if (!userProfileDTO.getName().equals(user.getUserDetails().getName())) {
            user.getUserDetails().setName(userProfileDTO.getName());
        }
        if (!userProfileDTO.getSurname().equals(user.getUserDetails().getSurname())) {
            user.getUserDetails().setSurname(userProfileDTO.getSurname());
        }
        if (!userProfileDTO.getAddress().equals(user.getUserDetails().getAddress())) {
            user.getUserDetails().setAddress(userProfileDTO.getAddress());
        }
        if (!userProfileDTO.getPhone().equals(user.getUserDetails().getPhone())) {
            user.getUserDetails().setPhone(userProfileDTO.getPhone());
        }
    }

    private boolean checkAmountAdministratorUsers(User user) {
        if (user.getRole() == ADMINISTRATOR) {
            Integer amountAdmins = userRepository.getAmountElements(user.getRole());
            if (amountAdmins == 1) {
                return true;
            }
        }
        return false;
    }

}
