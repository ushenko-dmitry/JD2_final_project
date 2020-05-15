package ru.mail.dimaushenko.service.converter;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.mail.dimaushenko.repository.constants.UserRoleEnum;
import ru.mail.dimaushenko.service.converter.UserRoleConverter;
import ru.mail.dimaushenko.service.converter.impl.UserRoleConverterImpl;
import ru.mail.dimaushenko.service.model.UserRoleEnumDTO;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleConverterImplTest {

    private UserRoleConverter userRoleConverter = new UserRoleConverterImpl();

    @Before
    public void setUp() {
    }

    @Test
    public void testGetDTOFromObject_ADMINISTRATOR_Input() {
        UserRoleEnum userRoleEnum = UserRoleEnum.ADMINISTRATOR;
        UserRoleEnumDTO resultUserRoleEnumDTO = UserRoleEnumDTO.ADMINISTRATOR;
        UserRoleEnumDTO userRoleEnumDTO = userRoleConverter.getDTOFromObject(userRoleEnum);
        assertThat(userRoleEnumDTO).isEqualTo(resultUserRoleEnumDTO);
    }

    @Test
    public void testGetDTOFromObject_CUSTOMER_USER_Input() {
        UserRoleEnum userRoleEnum = UserRoleEnum.CUSTOMER_USER;
        UserRoleEnumDTO resultUserRoleEnumDTO = UserRoleEnumDTO.CUSTOMER_USER;
        UserRoleEnumDTO userRoleEnumDTO = userRoleConverter.getDTOFromObject(userRoleEnum);
        assertThat(userRoleEnumDTO).isEqualTo(resultUserRoleEnumDTO);
    }

    @Test
    public void testGetDTOFromObject_SALE_USER_Input() {
        UserRoleEnum userRoleEnum = UserRoleEnum.SALE_USER;
        UserRoleEnumDTO resultUserRoleEnumDTO = UserRoleEnumDTO.SALE_USER;
        UserRoleEnumDTO userRoleEnumDTO = userRoleConverter.getDTOFromObject(userRoleEnum);
        assertThat(userRoleEnumDTO).isEqualTo(resultUserRoleEnumDTO);
    }

    @Test
    public void testGetDTOFromObject_SECURE_API_USER_Input() {
        UserRoleEnum userRoleEnum = UserRoleEnum.SECURE_API_USER;
        UserRoleEnumDTO resultUserRoleEnumDTO = UserRoleEnumDTO.SECURE_API_USER;
        UserRoleEnumDTO userRoleEnumDTO = userRoleConverter.getDTOFromObject(userRoleEnum);
        assertThat(userRoleEnumDTO).isEqualTo(resultUserRoleEnumDTO);
    }

    @Test
    public void testGetDTOFromObject_Null_Input() {
        UserRoleEnum userRoleEnum = null;
        UserRoleEnumDTO userRoleEnumDTO = userRoleConverter.getDTOFromObject(userRoleEnum);
        assertThat(userRoleEnumDTO).isNull();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetDTOFromObject_List() {
        List<UserRoleEnum> userRoleEnums = new ArrayList<>();
        userRoleConverter.getDTOFromObject(userRoleEnums);
    }

    @Test
    public void testGetObjectFromDTO_ADMINISTRATOR_Input() {
        UserRoleEnumDTO userRoleEnumDTO = UserRoleEnumDTO.ADMINISTRATOR;
        UserRoleEnum resultUserRoleEnum = UserRoleEnum.ADMINISTRATOR;
        UserRoleEnum userRoleEnum = userRoleConverter.getObjectFromDTO(userRoleEnumDTO);
        assertThat(userRoleEnum).isEqualTo(resultUserRoleEnum);
    }

    @Test
    public void testGetObjectFromDTO_CUSTOMER_USER_Input() {
        UserRoleEnumDTO userRoleEnumDTO = UserRoleEnumDTO.CUSTOMER_USER;
        UserRoleEnum resultUserRoleEnum = UserRoleEnum.CUSTOMER_USER;
        UserRoleEnum userRoleEnum = userRoleConverter.getObjectFromDTO(userRoleEnumDTO);
        assertThat(userRoleEnum).isEqualTo(resultUserRoleEnum);
    }

    @Test
    public void testGetObjectFromDTO_SALE_USER_Input() {
        UserRoleEnumDTO userRoleEnumDTO = UserRoleEnumDTO.SALE_USER;
        UserRoleEnum resultUserRoleEnum = UserRoleEnum.SALE_USER;
        UserRoleEnum userRoleEnum = userRoleConverter.getObjectFromDTO(userRoleEnumDTO);
        assertThat(userRoleEnum).isEqualTo(resultUserRoleEnum);
    }

    @Test
    public void testGetObjectFromDTO_SECURE_API_USER_Input() {
        UserRoleEnumDTO userRoleEnumDTO = UserRoleEnumDTO.SECURE_API_USER;
        UserRoleEnum resultUserRoleEnum = UserRoleEnum.SECURE_API_USER;
        UserRoleEnum userRoleEnum = userRoleConverter.getObjectFromDTO(userRoleEnumDTO);
        assertThat(userRoleEnum).isEqualTo(resultUserRoleEnum);
    }

    @Test
    public void testGetObjectFromDTO_Null_Input() {
        UserRoleEnumDTO userRoleEnumDTO = null;
        UserRoleEnum userRoleEnum = userRoleConverter.getObjectFromDTO(userRoleEnumDTO);
        assertThat(userRoleEnum).isNull();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetObjectFromDTO_List() {
        List<UserRoleEnumDTO> userRoleEnumDTOs = new ArrayList<>();
        userRoleConverter.getObjectFromDTO(userRoleEnumDTOs);
    }

}
