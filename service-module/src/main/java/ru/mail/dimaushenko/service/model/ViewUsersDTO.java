package ru.mail.dimaushenko.service.model;

import java.util.List;

public class ViewUsersDTO {

    private PaginationDTO pagination;
    private List<UserDTO> users;

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> userDTOs) {
        this.users = userDTOs;
    }

}
