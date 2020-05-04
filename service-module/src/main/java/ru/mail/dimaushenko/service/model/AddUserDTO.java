package ru.mail.dimaushenko.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.FIELD_SIZE_FROM_1_TO_20;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.FIELD_SIZE_FROM_1_TO_40;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.FIELD_SIZE_FROM_1_TO_50;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.ROLE_NOT_SELECT;

public class AddUserDTO {

    @Size(min = 1, max = 50, message = FIELD_SIZE_FROM_1_TO_50)
    private String email;
    private String password;
    @Size(min = 1, max = 20, message = FIELD_SIZE_FROM_1_TO_20)
    private String name;
    @Size(min = 1, max = 40, message = FIELD_SIZE_FROM_1_TO_40)
    private String surname;
    @Size(min = 1, max = 40, message = FIELD_SIZE_FROM_1_TO_40)
    private String patronymic;
    @NotNull(message = ROLE_NOT_SELECT)
    private UserRoleEnumDTO role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleEnumDTO getRole() {
        return role;
    }

    public void setRole(UserRoleEnumDTO role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

}
