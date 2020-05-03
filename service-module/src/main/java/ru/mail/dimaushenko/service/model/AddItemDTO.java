package ru.mail.dimaushenko.service.model;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class AddItemDTO {

    @Size(min = 1, max = 255, message = "the description must be from 1 to 255")
    private String name;
    @Size(min = 1, max = 200, message = "the description must be from 1 to 200")
    private String description;
    @Min(value = 0, message = "the price cannot be less than zero")
    private BigDecimal price;
    private UserDTO user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
