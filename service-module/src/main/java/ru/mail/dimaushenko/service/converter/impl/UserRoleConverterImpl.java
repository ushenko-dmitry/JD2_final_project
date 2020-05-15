package ru.mail.dimaushenko.service.converter.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.constants.UserRoleEnum;
import ru.mail.dimaushenko.service.converter.UserRoleConverter;
import ru.mail.dimaushenko.service.model.UserRoleEnumDTO;
import static ru.mail.dimaushenko.service.model.UserRoleEnumDTO.ADMINISTRATOR;
import static ru.mail.dimaushenko.service.model.UserRoleEnumDTO.CUSTOMER_USER;
import static ru.mail.dimaushenko.service.model.UserRoleEnumDTO.SALE_USER;
import static ru.mail.dimaushenko.service.model.UserRoleEnumDTO.SECURE_API_USER;

@Component
public class UserRoleConverterImpl implements UserRoleConverter {

    @Override
    public UserRoleEnumDTO getDTOFromObject(UserRoleEnum role) {
        if (role != null) {
            switch (role) {
                case ADMINISTRATOR:
                    return UserRoleEnumDTO.ADMINISTRATOR;
                case CUSTOMER_USER:
                    return UserRoleEnumDTO.CUSTOMER_USER;
                case SALE_USER:
                    return UserRoleEnumDTO.SALE_USER;
                case SECURE_API_USER:
                    return UserRoleEnumDTO.SECURE_API_USER;
                default:
                    return null;
            }
        }
        return null;
    }

    @Override
    public List<UserRoleEnumDTO> getDTOFromObject(List<UserRoleEnum> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserRoleEnum getObjectFromDTO(UserRoleEnumDTO role) {
        if (role != null) {
            switch (role) {
                case ADMINISTRATOR:
                    return UserRoleEnum.ADMINISTRATOR;
                case CUSTOMER_USER:
                    return UserRoleEnum.CUSTOMER_USER;
                case SALE_USER:
                    return UserRoleEnum.SALE_USER;
                case SECURE_API_USER:
                    return UserRoleEnum.SECURE_API_USER;
                default:
                    return null;
            }
        }
        return null;
    }

    @Override
    public List<UserRoleEnum> getObjectFromDTO(List<UserRoleEnumDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
