package ru.mail.dimaushenko.service.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.FIELD_IS_EMPTY;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.PASSWORD_FORMAT;

public class UserPasswordDTO {

    private Long id;
    @Size(min = 1, message = FIELD_IS_EMPTY)
    @Pattern(
            regexp = "^[a-zA-Z0-9!@#$%^&*()_]{1,}$",
            message = PASSWORD_FORMAT
    )
    private String password;
    @Size(min = 1, message = FIELD_IS_EMPTY)
    private String passwordConfirm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

}
