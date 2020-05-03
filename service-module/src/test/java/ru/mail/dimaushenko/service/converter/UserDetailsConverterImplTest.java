package ru.mail.dimaushenko.service.converter;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.mail.dimaushenko.repository.model.UserDetails;
import ru.mail.dimaushenko.service.converter.impl.UserDetailsConverterImpl;
import static ru.mail.dimaushenko.service.converter.util.UserDetailsUtil.getUserDetails;
import static ru.mail.dimaushenko.service.converter.util.UserDetailsUtil.getUserDetailsDTO;
import static ru.mail.dimaushenko.service.converter.util.UserDetailsUtil.getValidUserDetails;
import static ru.mail.dimaushenko.service.converter.util.UserDetailsUtil.getValidUserDetailsDTO;
import ru.mail.dimaushenko.service.model.UserDetailsDTO;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsConverterImplTest {

    private UserDetailsConverter userDetailsConverter;

    @Before
    public void setUp() {
        userDetailsConverter = new UserDetailsConverterImpl();
    }

    @Test
    public void testGetDTOFromObject_UserDetails_Input() {
        UserDetails userDetails = getValidUserDetails();
        UserDetailsDTO returnUserDetailsDTO = getUserDetailsDTO(userDetails);
        UserDetailsDTO userDetailsDTO = userDetailsConverter.getDTOFromObject(userDetails);
        assertThat(userDetailsDTO).isEqualTo(returnUserDetailsDTO);
    }

    @Test
    public void testGetDTOFromObject_NULL_Input() {
        UserDetails userDetails = null;
        UserDetailsDTO userDetailsDTO = userDetailsConverter.getDTOFromObject(userDetails);
        assertThat(userDetailsDTO).isNull();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetDTOFromObject_List() {
        List<UserDetails> userDetailses = new ArrayList<>();
        userDetailsConverter.getDTOFromObject(userDetailses);
    }

    @Test
    public void testGetObjectFromDTO_UserDetailsDTO_Input() {
        UserDetailsDTO userDetailsDTO = getValidUserDetailsDTO();
        UserDetails returnUserDetails = getUserDetails(userDetailsDTO);
        UserDetails userDetails = userDetailsConverter.getObjectFromDTO(userDetailsDTO);
        assertThat(userDetails).isEqualTo(returnUserDetails);
    }

    @Test
    public void testGetObjectFromDTO_NULL_Input() {
        UserDetailsDTO userDetailsDTO = null;
        UserDetails userDetails = userDetailsConverter.getObjectFromDTO(userDetailsDTO);
        assertThat(userDetails).isNull();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetObjectFromDTO_List() {
        List<UserDetailsDTO> userDetailsDTOs = new ArrayList<>();
        userDetailsConverter.getObjectFromDTO(userDetailsDTOs);
    }

}
