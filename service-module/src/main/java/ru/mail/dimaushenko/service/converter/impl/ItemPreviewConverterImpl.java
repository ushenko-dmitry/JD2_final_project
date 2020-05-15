package ru.mail.dimaushenko.service.converter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.service.converter.ItemPreviewConverter;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.model.ItemPreviewDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

@Component
public class ItemPreviewConverterImpl implements ItemPreviewConverter {

    private final UserConverter userConverter;

    public ItemPreviewConverterImpl(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public ItemPreviewDTO getDTOFromObject(Item item) {
        ItemPreviewDTO itemPreviewDTO = new ItemPreviewDTO();
        itemPreviewDTO.setId(item.getId());
        itemPreviewDTO.setUuid(UUID.fromString(item.getUuid()));
        itemPreviewDTO.setName(item.getName());
        itemPreviewDTO.setPrice(item.getPrice());
        UserDTO user = userConverter.getDTOFromObject(item.getUser());
        itemPreviewDTO.setUser(user);
        return itemPreviewDTO;
    }

    @Override
    public List<ItemPreviewDTO> getDTOFromObject(List<Item> items) {
        List<ItemPreviewDTO> itemPreviewDTOList = new ArrayList<>();
        for (Item item : items) {
            itemPreviewDTOList.add(getDTOFromObject(item));
        }
        return itemPreviewDTOList;
    }

    @Override
    public Item getObjectFromDTO(ItemPreviewDTO modelDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getObjectFromDTO(List<ItemPreviewDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
