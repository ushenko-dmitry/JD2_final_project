package ru.mail.dimaushenko.service.converter.util;

import ru.mail.dimaushenko.repository.constants.UserRoleEnum;
import ru.mail.dimaushenko.service.model.UserRoleEnumDTO;

public interface Constants {

    long USER_ID = 1L;
    String USER_PASSWORD = "password";
    String ENCODE_PASSWORD = "encode_password";
    String USER_EMAIL = "email";
    UserRoleEnumDTO USER_DTO_ROLE = UserRoleEnumDTO.SALE_USER;
    UserRoleEnum USER_ROLE = UserRoleEnum.SALE_USER;

    String USER_DETAILS_PHONE = "Phone";
    String USER_DETAILS_ADDRESS = "Address";
    String USER_DETAILS_PATRONYMIC = "Patronymic";
    String USER_DETAILS_SURNAME = "Surname";
    String USER_DETAILS_NAME = "Name";

}
