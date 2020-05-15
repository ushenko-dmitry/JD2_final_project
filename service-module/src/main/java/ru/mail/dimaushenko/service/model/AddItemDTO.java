package ru.mail.dimaushenko.service.model;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.FIELD_SIZE_FROM_1_TO_200;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.FIELD_SIZE_FROM_1_TO_255;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.PRICE_MIN;

public class AddItemDTO {

    @Size(min = 1, max = 255, message = FIELD_SIZE_FROM_1_TO_255)
    private String name;
    @Size(min = 1, max = 200, message = FIELD_SIZE_FROM_1_TO_200)
    private String description;
    @Min(value = 0, message = PRICE_MIN)
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
