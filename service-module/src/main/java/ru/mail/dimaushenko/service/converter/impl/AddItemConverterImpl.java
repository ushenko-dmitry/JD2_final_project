package ru.mail.dimaushenko.service.converter.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.ItemDetails;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.converter.AddItemConverter;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

@Component
public class AddItemConverterImpl implements AddItemConverter {

    private final UserConverter userConverter;

    public AddItemConverterImpl(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public AddItemDTO getDTOFromObject(Item model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AddItemDTO> getDTOFromObject(List<Item> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getObjectFromDTO(AddItemDTO modelDTO) {
        Item item = new Item();
        item.setName(modelDTO.getName());
        item.setPrice(modelDTO.getPrice());
        item.setUuid(UUID.randomUUID().toString());

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setDescription(modelDTO.getDescription());
        itemDetails.setItem(item);
        item.setItemDetails(itemDetails);

        UserDTO userDTO = modelDTO.getUser();
        User user = userConverter.getObjectFromDTO(userDTO);
        item.setUser(user);
        return item;
    }

    @Override
    public List<Item> getObjectFromDTO(List<AddItemDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
