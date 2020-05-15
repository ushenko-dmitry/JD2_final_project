package ru.mail.dimaushenko.service.converter.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.ItemDetails;
import ru.mail.dimaushenko.service.converter.ItemDetailsConverter;
import ru.mail.dimaushenko.service.model.ItemDetailsDTO;

@Component
public class ItemDetailsConverterImpl implements ItemDetailsConverter {

    @Override
    public ItemDetailsDTO getDTOFromObject(ItemDetails itemDetails) {
        if (itemDetails != null) {
            ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();
            itemDetailsDTO.setItemId(itemDetails.getItemId());
            itemDetailsDTO.setDescription(itemDetails.getDescription());
            return itemDetailsDTO;
        }
        return null;
    }

    @Override
    public List<ItemDetailsDTO> getDTOFromObject(List<ItemDetails> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemDetails getObjectFromDTO(ItemDetailsDTO itemDetailsDTO) {
        if (itemDetailsDTO != null) {
            ItemDetails itemDetails = new ItemDetails();
            itemDetails.setItemId(itemDetailsDTO.getItemId());
            itemDetails.setDescription(itemDetailsDTO.getDescription());
            return itemDetails;
        }
        return null;
    }

    @Override
    public List<ItemDetails> getObjectFromDTO(List<ItemDetailsDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
