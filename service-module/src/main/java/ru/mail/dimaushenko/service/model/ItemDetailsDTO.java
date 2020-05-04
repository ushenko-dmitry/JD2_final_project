package ru.mail.dimaushenko.service.model;

import java.util.Objects;
import javax.validation.constraints.Size;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.FIELD_SIZE_FROM_1_TO_200;

public class ItemDetailsDTO {

    private Long itemId;
    @Size(min = 1, max = 200, message = FIELD_SIZE_FROM_1_TO_200)
    private String description;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.itemId);
        hash = 43 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemDetailsDTO other = (ItemDetailsDTO) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.itemId, other.itemId)) {
            return false;
        }
        return true;
    }

}
