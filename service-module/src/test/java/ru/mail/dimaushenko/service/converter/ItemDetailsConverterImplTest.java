package ru.mail.dimaushenko.service.converter;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.mail.dimaushenko.repository.model.ItemDetails;
import ru.mail.dimaushenko.service.converter.impl.ItemDetailsConverterImpl;
import static ru.mail.dimaushenko.service.converter.util.ItemDetailsUtil.getValidItemDetails;
import static ru.mail.dimaushenko.service.converter.util.ItemDetailsUtil.getValidItemDetailsDTO;
import ru.mail.dimaushenko.service.model.ItemDetailsDTO;

@RunWith(MockitoJUnitRunner.class)
public class ItemDetailsConverterImplTest {

    private ItemDetailsConverter itemDetailsConverter;

    @Before
    public void setUp() {
        itemDetailsConverter = new ItemDetailsConverterImpl();
    }

    @Test
    public void testGetDTOFromObject_ValidItemDetails_Input() {
        ItemDetails itemDetails = getValidItemDetails();
        ItemDetailsDTO returnedItemDetailsDTO = getValidItemDetailsDTO(itemDetails);
        ItemDetailsDTO itemDetailsDTO = itemDetailsConverter.getDTOFromObject(itemDetails);
        assertThat(itemDetailsDTO).isNotNull();
        assertThat(itemDetailsDTO).isEqualTo(returnedItemDetailsDTO);
    }

    @Test
    public void testGetDTOFromObject_Null_Input() {
        ItemDetails itemDetails = null;
        ItemDetailsDTO itemDetailsDTO = itemDetailsConverter.getDTOFromObject(itemDetails);
        assertThat(itemDetailsDTO).isNull();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetDTOFromObject_List() {
        List<ItemDetails> itemDetailsList = new ArrayList<>();
        itemDetailsConverter.getDTOFromObject(itemDetailsList);
    }

    @Test
    public void testGetObjectFromDTO_ValidItemDetailsDTO_Input() {
        ItemDetailsDTO itemDetailsDTO = getValidItemDetailsDTO();
        ItemDetails returnedItemDetails = getValidItemDetails(itemDetailsDTO);
        ItemDetails itemDetails = itemDetailsConverter.getObjectFromDTO(itemDetailsDTO);
        assertThat(itemDetails).isNotNull();
        assertThat(itemDetails).isEqualTo(returnedItemDetails);
    }

    @Test
    public void testGetObjectFromDTO_Null_Input() {
        ItemDetails itemDetails = null;
        ItemDetailsDTO itemDetailsDTO = itemDetailsConverter.getDTOFromObject(itemDetails);
        assertThat(itemDetailsDTO).isNull();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetObjectFromDTO_List() {
        List<ItemDetailsDTO> itemDetailsDTOList = new ArrayList<>();
        itemDetailsConverter.getObjectFromDTO(itemDetailsDTOList);
    }

}
