package ru.mail.dimaushenko.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddUserDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String email;
    private String password;
    @NotNull
    @Size(min = 1, max = 20)
    private String name;
    @NotNull
    @Size(min = 1, max = 40)
    private String surname;
    @NotNull
    @Size(min = 1, max = 40)
    private String patronymic;
    @NotNull
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
