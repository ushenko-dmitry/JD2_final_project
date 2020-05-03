package ru.mail.dimaushenko.service.impl;

import java.util.List;
import java.util.UUID;
import static java.util.UUID.randomUUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.BasketRepository;
import ru.mail.dimaushenko.repository.ItemRepository;
import ru.mail.dimaushenko.repository.UserRepository;
import static ru.mail.dimaushenko.repository.constants.SortOrderEnum.ASC;
import ru.mail.dimaushenko.repository.model.Basket;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.ItemService;
import ru.mail.dimaushenko.service.converter.AddBasketConverter;
import ru.mail.dimaushenko.service.converter.AddItemConverter;
import ru.mail.dimaushenko.service.converter.ConverterFacade;
import ru.mail.dimaushenko.service.converter.ItemConverter;
import ru.mail.dimaushenko.service.converter.ItemPreviewConverter;
import ru.mail.dimaushenko.service.converter.ItemXMLConverter;
import ru.mail.dimaushenko.service.converter.PaginationConverter;
import ru.mail.dimaushenko.service.model.AddBasketDTO;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemPreviewDTO;
import ru.mail.dimaushenko.service.model.ItemXmlDTO;
import ru.mail.dimaushenko.service.model.ItemXmlDTOList;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import static ru.mail.dimaushenko.service.utils.XmlParserUtil.getItemXmlDTO;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final ConverterFacade converterFacade;

    public ItemServiceImpl(
            ItemRepository itemRepository,
            UserRepository userRepository,
            BasketRepository basketRepository,
            ConverterFacade converterFacade
    ) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.basketRepository = basketRepository;
        this.converterFacade = converterFacade;
    }

    @Override
    public void addItemFromFile(String userEmail, String filename) {
        User user = userRepository.getUserByEmail(userEmail);
        ItemXmlDTOList itemXmlDTOList = getItemXmlDTO(filename);
        ItemXMLConverter itemXMLConverter = converterFacade.getItemXMLConverter();
        for (ItemXmlDTO itemDTO : itemXmlDTOList.getItems()) {
            Item item = itemXMLConverter.getObjectFromDTO(itemDTO);
            item.setUser(user);
            itemRepository.persist(item);
        }
    }

    @Override
    public ItemDTO addItem(AddItemDTO addItemDTO) {
        AddItemConverter addItemConverter = converterFacade.getAddItemConverter();
        Item item = addItemConverter.getObjectFromDTO(addItemDTO);
        User user = userRepository.findById(item.getUser().getId());
        item.setUser(user);
        itemRepository.persist(item);
        ItemConverter itemConverter = converterFacade.getItemConverter();
        return itemConverter.getDTOFromObject(item);
    }

    @Override
    public List<ItemPreviewDTO> getItemPreviews(PaginationDTO paginationDTO) {
        PaginationConverter paginationConverter = converterFacade.getPaginationConverter();
        Pagination pagination = paginationConverter.getObjectFromDTO(paginationDTO);
        List<Item> itemList = itemRepository.getItems(pagination, ASC);
        ItemPreviewConverter itemPreviewConverter = converterFacade.getItemPreviewConverter();
        return itemPreviewConverter.getDTOFromObject(itemList);
    }

    @Override
    public ItemDTO getItem(UUID uuid) {
        Item item = itemRepository.getItemByUUID(uuid);
        ItemConverter itemConverter = converterFacade.getItemConverter();
        return itemConverter.getDTOFromObject(item);
    }

    @Override
    public List<ItemDTO> getItems() {
        List<Item> itemList = itemRepository.findAll();
        ItemConverter itemConverter = converterFacade.getItemConverter();
        return itemConverter.getDTOFromObject(itemList);
    }

    @Override
    public ItemDTO getItem(Long id) {
        Item item = itemRepository.findById(id);
        ItemConverter itemConverter = converterFacade.getItemConverter();
        return itemConverter.getDTOFromObject(item);
    }

    @Override
    public Boolean deleteItem(Long id) {
        Item item = itemRepository.findById(id);
        item.setUser(null);
        itemRepository.remove(item);
        return true;
    }

    @Override
    public Boolean deleteItem(UUID uuid) {
        Item item = itemRepository.getItemByUUID(uuid);
        item.setUser(null);
        itemRepository.remove(item);
        return true;
    }

    @Override
    public Integer getAmountItems() {
        return itemRepository.getAmountElements();
    }

    @Override
    public ItemDTO copyItem(ItemDTO itemDTO) {
        itemDTO.setUuid(randomUUID());
        ItemConverter itemConverter = converterFacade.getItemConverter();
        Item item = itemConverter.getObjectFromDTO(itemDTO);
        item.setId(null);
        item.getItemDetails().setItemId(null);
        User user = userRepository.findById(item.getUser().getId());
        item.setUser(user);
        itemRepository.persist(item);
        return itemConverter.getDTOFromObject(item);
    }

    @Override
    public void addToBusket(AddBasketDTO addBusket) {
        User customerUser = userRepository.findById(addBusket.getUser().getId());
        User salerUser = userRepository.findById(addBusket.getItem().getUser().getId());
        AddBasketConverter addBasketConverter = converterFacade.getAddBasketConverter();
        Basket basket = addBasketConverter.getObjectFromDTO(addBusket);
        basket.setUser(customerUser);
        basket.getOrderedItem().setUser(salerUser);
        basketRepository.persist(basket);
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO) {
        Item item = itemRepository.getItemByUUID(itemDTO.getUuid());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.getItemDetails().setDescription(itemDTO.getItemDetails().getDescription());
        ItemConverter itemConverter = converterFacade.getItemConverter();
        return itemConverter.getDTOFromObject(item);
    }

}
