package ru.mail.dimaushenko.webmodule.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.UserDTO;
import ru.mail.dimaushenko.service.model.ViewUsersDTO;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_CURRENT_PAGE;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_USERS_PER_PAGE;

@Controller
@RequestMapping("/users")
@SessionAttributes(types = ViewUsersDTO.class)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(
            ViewUsersDTO viewUsersDTO,
            Model model) {
        PaginationDTO pagination = setPagination(viewUsersDTO.getPagination());
        viewUsersDTO.setPagination(pagination);
        List<UserDTO> users = userService.getUsersSortByEmail(pagination);
        viewUsersDTO.setUsers(users);
        model.addAttribute("viewUsers", viewUsersDTO);
        return "users";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("new_user", new AddUserDTO());
        return "user_add";
    }

    @PostMapping("/add")
    public String addUser(
            @Valid @ModelAttribute(name = "new_user") AddUserDTO addUser,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "user_add";
        }
        userService.addUser(addUser);
        return "redirect:/users";
    }

    @GetMapping("/{id}/update")
    public String updateUser(@PathVariable Long id, Model model) {
        UserDTO user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user_update";
    }

    @PostMapping("/{id}/update")
    public String updateUser(
            @PathVariable Long id,
            @ModelAttribute(name = "user") UserDTO user,
            Model model
    ) {
        boolean isUserUpdate = userService.updateUserRole(user);
        if (!isUserUpdate) {
            return "redirect:/users/" + id + "/update?error";
        }
        model.addAttribute("user", user);
        return "redirect:/users/" + id + "/update";
    }

    @PostMapping("/{id}/resetPassword")
    public String resetPassword(@PathVariable Long id, Model model) {
        boolean isReset = userService.resetPassword(id);

        UserDTO user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user_update";
    }

    @PostMapping("/delete")
    public String deleteUsers(
            @RequestParam(name = "deleteCheckbox", required = false) String[] strings
    ) {
        if (strings != null) {
            for (String string : strings) {
                Long id = Long.parseLong(string);
                userService.deleteUser(id);
            }
        }
        return "redirect:/users";
    }

    private PaginationDTO setPagination(PaginationDTO pagination) {
        if (pagination == null) {
            pagination = new PaginationDTO();
        }
        if (pagination.getCurrentPage() == null) {
            pagination.setCurrentPage(DEFAULT_CURRENT_PAGE);
        }
        if (pagination.getElementsPerPage() == null) {
            pagination.setElementsPerPage(DEFAULT_USERS_PER_PAGE);
        }
        Integer amountUsers = userService.getAmountUsers();
        Integer amountPages = amountUsers / pagination.getElementsPerPage();
        if (amountUsers % pagination.getElementsPerPage() > 0) {
            amountPages++;
        }
        if (amountPages == 0) {
            amountPages++;
        }
        pagination.setPageNumbers(new ArrayList<>());
        for (int i = 1; i <= amountPages; i++) {
            pagination.getPageNumbers().add(i);
        }
        if (pagination.getPageNumbers().size() < pagination.getCurrentPage()) {
            pagination.setCurrentPage(DEFAULT_CURRENT_PAGE);
        }
        return pagination;
    }

}
