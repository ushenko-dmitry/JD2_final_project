package ru.mail.dimaushenko.service.converter.util;

import ru.mail.dimaushenko.repository.model.ItemDetails;
import ru.mail.dimaushenko.service.model.ItemDetailsDTO;

public class ItemDetailsUtil {

    public static ItemDetails getValidItemDetails() {
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setItemId(1L);
        itemDetails.setDescription("Description");
        itemDetails.setItem(null);
        return itemDetails;
    }

    public static ItemDetails getValidItemDetails(ItemDetailsDTO itemDetailsDTO) {
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setItemId(itemDetailsDTO.getItemId());
        itemDetails.setDescription(itemDetailsDTO.getDescription());
        itemDetails.setItem(null);
        return itemDetails;
    }

    public static ItemDetailsDTO getValidItemDetailsDTO() {
        ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();
        itemDetailsDTO.setItemId(1L);
        itemDetailsDTO.setDescription("Description");
        return itemDetailsDTO;
    }

    public static ItemDetailsDTO getValidItemDetailsDTO(ItemDetails itemDetails) {
        ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();
        itemDetailsDTO.setItemId(itemDetails.getItemId());
        itemDetailsDTO.setDescription(itemDetails.getDescription());
        return itemDetailsDTO;
    }

}
