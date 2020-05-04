package ru.mail.dimaushenko.webmodule.controller;

import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.model.AppUser;
import ru.mail.dimaushenko.service.model.UserPasswordDTO;
import ru.mail.dimaushenko.service.model.UserProfileDTO;
import ru.mail.dimaushenko.webmodule.validator.ChangePasswordValidator;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final ChangePasswordValidator passwordValidator;

    public ProfileController(UserService userService, ChangePasswordValidator passwordValidator) {
        this.userService = userService;
        this.passwordValidator = passwordValidator;
    }

    @GetMapping
    public String showProfile(
            Authentication authentication,
            Model model
    ) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        UserProfileDTO userProfile = userService.getUserProfile(appUser.getUsername());
        model.addAttribute("user_profile", userProfile);
        UserPasswordDTO userPassword = new UserPasswordDTO();
        userPassword.setId(userProfile.getId());
        model.addAttribute("new_password", userPassword);
        return "profile";
    }

    @PostMapping("/change_password")
    public String changePassword(
            @Valid @ModelAttribute(name = "new_password") UserPasswordDTO userPasswordDTO,
            BindingResult bindingResult,
            @ModelAttribute(name = "user_profile") UserProfileDTO userProfile,
            Model model
    ) {
        passwordValidator.validate(userPasswordDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        boolean isUpdated = userService.updateUserPassword(userPasswordDTO);
        if (!isUpdated) {
            return "profile";
        }
        return "redirect:/profile";
    }

    @GetMapping("/edit")
    public String updateProfile(
            Authentication authentication,
            Model model
    ) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        UserProfileDTO userProfile = userService.getUserProfile(appUser.getUsername());
        model.addAttribute("user_profile", userProfile);
        UserPasswordDTO userPassword = new UserPasswordDTO();
        userPassword.setId(userProfile.getId());
        model.addAttribute("new_password", userPassword);
        return "profile_edit";
    }

    @PostMapping("/edit")
    public String updateProfile(
            @Valid @ModelAttribute(name = "user_profile") UserProfileDTO userProfile,
            BindingResult bindingResult,
            @ModelAttribute(name = "new_password") UserPasswordDTO userPasswordDTO,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "profile_edit";
        }
        boolean isUpdated = userService.updateUserDetails(userProfile);
        if (!isUpdated) {
            return "profile_edit";
        }
        return "redirect:/profile";
    }

}
