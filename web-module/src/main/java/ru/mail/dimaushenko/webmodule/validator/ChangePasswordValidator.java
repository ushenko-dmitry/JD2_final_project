package ru.mail.dimaushenko.webmodule.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mail.dimaushenko.service.model.UserPasswordDTO;

@Component
public class ChangePasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return UserPasswordDTO.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserPasswordDTO userPassword = (UserPasswordDTO) o;
        if (!userPassword.getPassword().equals(userPassword.getPasswordConfirm())) {
            errors.rejectValue("password", "", "Passwords don't match");
            errors.rejectValue("passwordConfirm", "", "Passwords don't match");
        }
    }

}
