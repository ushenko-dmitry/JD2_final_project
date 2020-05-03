package ru.mail.dimaushenko.service.converter.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.ItemDetails;
import ru.mail.dimaushenko.service.converter.ItemXMLConverter;
import ru.mail.dimaushenko.service.model.ItemXmlDTO;

@Component
public class ItemXMLConverterImpl implements ItemXMLConverter {

    @Override
    public ItemXmlDTO getDTOFromObject(Item model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemXmlDTO> getDTOFromObject(List<Item> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getObjectFromDTO(ItemXmlDTO itemXmlDTO) {
        Item item = new Item();
        item.setName(itemXmlDTO.getName());
        item.setPrice(itemXmlDTO.getPrice());
        item.setUuid(UUID.randomUUID().toString());

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setDescription(itemXmlDTO.getDescription());
        itemDetails.setItem(item);
        item.setItemDetails(itemDetails);
        return item;
    }

    @Override
    public List<Item> getObjectFromDTO(List<ItemXmlDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
