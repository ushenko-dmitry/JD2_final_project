package ru.mail.dimaushenko.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.FIELD_MIN_1;

public class AddBasketDTO {

    private ItemDTO item;
    private UserDTO user;
    @Min(value = 1, message = FIELD_MIN_1)
    @Positive
    private Long amount;

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
