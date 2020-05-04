package ru.mail.dimaushenko.service.model;

import java.util.Objects;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.FIELD_SIZE_FROM_0_TO_255;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.FIELD_SIZE_FROM_1_TO_20;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.FIELD_SIZE_FROM_1_TO_40;
import static ru.mail.dimaushenko.service.constants.ErrorMessage.PHONE_FORMAT;

public class UserDetailsDTO {

    private Long userId;
    @Size(min = 1, max = 20, message = FIELD_SIZE_FROM_1_TO_20)
    private String name;
    @Size(min = 1, max = 40, message = FIELD_SIZE_FROM_1_TO_40)
    private String surname;
    @Size(min = 1, max = 40, message = FIELD_SIZE_FROM_1_TO_40)
    private String patronymic;
    @Size(min = 0, max = 40, message = FIELD_SIZE_FROM_0_TO_255)
    private String address;
    @Pattern(
            regexp = "^([\\+]{0,1}((375){1}|(\\(375\\)){1})([0-9]{2}|([-\\ ]{1}[0-9]{2}[-\\ ]{1})|(\\([0-9]{2}\\)))(([0-9]{7})|([0-9]{3}[-\\ ]{1}[0-9]{2}[-\\ ]{1}[0-9]{2})))$",
            message = PHONE_FORMAT
    )
    private String phone;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.userId);
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.surname);
        hash = 11 * hash + Objects.hashCode(this.patronymic);
        hash = 11 * hash + Objects.hashCode(this.address);
        hash = 11 * hash + Objects.hashCode(this.phone);
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
        final UserDetailsDTO other = (UserDetailsDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.patronymic, other.patronymic)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return true;
    }

}
