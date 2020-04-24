package ru.mail.dimaushenko.service.converter.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.ItemDetails;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.converter.ItemConverter;
import ru.mail.dimaushenko.service.converter.ItemDetailsConverter;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemDetailsDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

@Component
public class ItemConverterImpl implements ItemConverter {

    private final ItemDetailsConverter itemDetailsConverter;
    private final UserConverter userConverter;

    public ItemConverterImpl(
            ItemDetailsConverter itemDetailsConverter,
            UserConverter userConverter
    ) {
        this.itemDetailsConverter = itemDetailsConverter;
        this.userConverter = userConverter;
    }

    @Override
    public ItemDTO getDTOFromObject(Item item) {
        if (item != null) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setUuid(item.getUuid());
            itemDTO.setName(item.getName());
            itemDTO.setPrice(item.getPrice());

            ItemDetails itemDetails = item.getItemDetails();
            ItemDetailsDTO itemDetailsDTO = itemDetailsConverter.getDTOFromObject(itemDetails);
            itemDTO.setItemDetails(itemDetailsDTO);

            User user = item.getUser();
            UserDTO userDTO = userConverter.getDTOFromObject(user);
            itemDTO.setUser(userDTO);
            return itemDTO;
        }
        return null;
    }

    @Override
    public List<ItemDTO> getDTOFromObject(List<Item> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getObjectFromDTO(ItemDTO modelDTO) {
        if (modelDTO != null) {
            Item item = new Item();
            item.setId(modelDTO.getId());
            item.setUuid(modelDTO.getUuid());
            item.setName(modelDTO.getName());
            item.setPrice(modelDTO.getPrice());

            ItemDetailsDTO itemDetailsDTO = modelDTO.getItemDetails();
            ItemDetails itemDetails = itemDetailsConverter.getObjectFromDTO(itemDetailsDTO);
            itemDetails.setItem(item);
            item.setItemDetails(itemDetails);

            UserDTO userDTO = modelDTO.getUser();
            User user = userConverter.getObjectFromDTO(userDTO);
            item.setUser(user);
            return item;
        }
        return null;
    }

    @Override
    public List<Item> getObjectFromDTO(List<ItemDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
