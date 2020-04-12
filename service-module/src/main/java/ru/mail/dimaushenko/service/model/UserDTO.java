package ru.mail.dimaushenko.service.model;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private Long id;
    private String email;
    private String password;
    private UserRoleEnumDTO role;
    private UserDetailsDTO userDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UserDetailsDTO getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsDTO userDetails) {
        this.userDetails = userDetails;
    }

}
