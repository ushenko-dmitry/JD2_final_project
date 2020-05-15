package ru.mail.dimaushenko.service.constants;

public interface ErrorMessage {

    String FIELD_IS_EMPTY = "Field is empty";
    String FIELD_SIZE_FROM_0_TO_255 = "Field size must be from 0 to 255";
    String FIELD_SIZE_FROM_1_TO_255 = "Field size must be from 1 to 255";
    String FIELD_SIZE_FROM_1_TO_200 = "Field size must be from 1 to 200";
    String FIELD_SIZE_FROM_1_TO_50 = "Field size must be from 1 to 50";
    String FIELD_SIZE_FROM_1_TO_40 = "Field size must be from 1 to 40";
    String FIELD_SIZE_FROM_1_TO_20 = "Field size must be from 1 to 20";

    String FIELD_MIN_1 = "Amount must be >= 1";

    String PRICE_MIN = "The price cannot be less than zero";

    String ROLE_NOT_SELECT = "Role is not select";
    String PHONE_FORMAT = "Phone must have Bealrus format (+375(**)***-**-**)";
    String PASSWORD_FORMAT = "Please use A-Z, a-z, 0-9, and special symbols";

}
