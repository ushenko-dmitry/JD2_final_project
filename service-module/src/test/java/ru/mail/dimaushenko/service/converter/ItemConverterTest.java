package ru.mail.dimaushenko.service.converter;

import static java.math.BigDecimal.ONE;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.ItemDetails;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.converter.impl.ItemConverterImpl;
import static ru.mail.dimaushenko.service.converter.util.ItemDetailsUtil.getValidItemDetails;
import static ru.mail.dimaushenko.service.converter.util.ItemDetailsUtil.getValidItemDetailsDTO;
import static ru.mail.dimaushenko.service.converter.util.UserUtil.getValidUser;
import static ru.mail.dimaushenko.service.converter.util.UserUtil.getValidUserDTO;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemDetailsDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

@RunWith(MockitoJUnitRunner.class)
public class ItemConverterTest {

    private ItemConverter itemConverter;

    @Mock
    private ItemDetailsConverter itemDetailsConverter;
    @Mock
    private UserConverter userConverter;
    public static final Long ITEM_ID = 1L;
    public static final UUID ITEM_UUID = UUID.randomUUID();
    public static final String ITEM_NAME = "Name";

    @Before
    public void setup() {
        itemConverter = new ItemConverterImpl(
                itemDetailsConverter,
                userConverter
        );
    }

    @Test
    public void testGetDTOFromObject_ValidItem_Input() {
        Item item = getValidItem();
        ItemDTO returnedItemDTO = getValidItemDTO(item);
        when(itemDetailsConverter.getDTOFromObject(item.getItemDetails())).thenReturn(returnedItemDTO.getItemDetails());
        when(userConverter.getDTOFromObject(item.getUser())).thenReturn(returnedItemDTO.getUser());
        ItemDTO itemDTO = itemConverter.getDTOFromObject(item);
        assertThat(itemDTO).isEqualTo(returnedItemDTO);
    }

    private Item getValidItem() {
        Item item = new Item();
        item.setId(ITEM_ID);
        item.setName(ITEM_NAME);
        item.setPrice(ONE);
        item.setUuid(ITEM_UUID.toString());

        ItemDetails itemDetails = getValidItemDetails();
        item.setItemDetails(itemDetails);

        User user = getValidUser();
        item.setUser(user);
        return item;
    }

    private ItemDTO getValidItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setUuid(UUID.fromString(item.getUuid()));
        ItemDetailsDTO itemDetailsDTO = getValidItemDetailsDTO(item.getItemDetails());
        itemDTO.setItemDetails(itemDetailsDTO);
        UserDTO UserDTO = getValidUserDTO(item.getUser());
        itemDTO.setUser(UserDTO);
        return itemDTO;
    }

}
