package ru.mail.dimaushenko.service;

import java.util.List;
import java.util.UUID;
import ru.mail.dimaushenko.service.model.AddBasketDTO;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemPreviewDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;

public interface ItemService {

    void addItemFromFile(String userEmail, String filename);

    void addToBusket(AddBasketDTO addBusket);

    ItemDTO copyItem(ItemDTO item);

    Integer getAmountItems();

    ItemDTO addItem(AddItemDTO addItemDTO);

    List<ItemPreviewDTO> getItemPreviews(PaginationDTO paginationDTO);

    List<ItemDTO> getItems();

    ItemDTO getItem(UUID uuid);

    ItemDTO getItem(Long id);

    ItemDTO updateItem(ItemDTO itemDTO);

    Boolean deleteItem(Long id);

    Boolean deleteItem(UUID uuid);
}
