package ru.mail.dimaushenko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import ru.mail.dimaushenko.repository.BasketRepository;
import ru.mail.dimaushenko.repository.ItemRepository;
import ru.mail.dimaushenko.repository.UserRepository;
import static ru.mail.dimaushenko.repository.constants.SortOrderEnum.ASC;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.service.converter.ConverterFacade;
import ru.mail.dimaushenko.service.converter.ItemConverter;
import ru.mail.dimaushenko.service.converter.ItemPreviewConverter;
import ru.mail.dimaushenko.service.converter.PaginationConverter;
import ru.mail.dimaushenko.service.impl.ItemServiceImpl;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemPreviewDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BasketRepository basketRepository;
    @Mock
    private ConverterFacade converterFacade;
    @Mock
    private ItemConverter itemConverter;
    @Mock
    private ItemPreviewConverter itemPreviewConverter;
    @Mock
    private PaginationConverter paginationConverter;

    @Before
    public void setUp() {
        itemService = new ItemServiceImpl(
                itemRepository,
                userRepository,
                basketRepository,
                converterFacade
        );
        when(converterFacade.getItemConverter()).thenReturn(itemConverter);
        when(converterFacade.getItemPreviewConverter()).thenReturn(itemPreviewConverter);
        when(converterFacade.getPaginationConverter()).thenReturn(paginationConverter);
    }

    @Test
    public void testGetItemPreviews() {
        PaginationDTO paginationDTO = getPaginationDTO();
        Pagination pagination = getPagination(paginationDTO);
        when(paginationConverter.getObjectFromDTO(paginationDTO)).thenReturn(pagination);
        List<Item> itemList = new ArrayList<>();
        when(itemRepository.getItems(pagination, ASC)).thenReturn(itemList);
        List<ItemPreviewDTO> returnItemPreviewDTOList = new ArrayList<>();
        when(itemPreviewConverter.getDTOFromObject(itemList)).thenReturn(returnItemPreviewDTOList);
        List<ItemPreviewDTO> itemPreviewDTOList = itemService.getItemPreviews(paginationDTO);
        verify(itemRepository, times(1)).getItems(pagination, ASC);
        assertThat(itemPreviewDTOList).isEqualTo(returnItemPreviewDTOList);
    }

    @Test
    public void testGetItemByUUID() {
        UUID uuid = randomUUID();
        Item item = new Item();
        when(itemRepository.getItemByUUID(uuid)).thenReturn(item);
        ItemDTO returnedItemDTO = new ItemDTO();
        when(itemConverter.getDTOFromObject(item)).thenReturn(returnedItemDTO);
        ItemDTO itemDTO = itemService.getItem(uuid);
        assertThat(itemDTO).isNotNull();
        verify(itemRepository, times(1)).getItemByUUID(uuid);
    }

    @Test
    public void testGetItemById() {
        Long id = 1L;
        Item item = new Item();
        when(itemRepository.findById(id)).thenReturn(item);
        ItemDTO returnedItemDTO = new ItemDTO();
        when(itemConverter.getDTOFromObject(item)).thenReturn(returnedItemDTO);
        ItemDTO itemDTO = itemService.getItem(id);
        assertThat(itemDTO).isNotNull();
        verify(itemRepository, times(1)).findById(id);
    }

    @Test
    public void testGetItems() {
        List<Item> itemList = new ArrayList<>();
        when(itemRepository.findAll()).thenReturn(itemList);
        List<ItemDTO> returnedItemDTOList = new ArrayList<>();
        when(itemConverter.getDTOFromObject(itemList)).thenReturn(returnedItemDTOList);
        List<ItemDTO> itemDTOList = itemService.getItems();
        assertThat(itemDTOList).isNotNull();
        assertThat(itemDTOList).isEqualTo(returnedItemDTOList);
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteItemByUUID() {
        Item item = new Item();
        when(itemRepository.getItemByUUID(any())).thenReturn(item);
        Boolean isDeleteItem = itemService.deleteItem(UUID.randomUUID());
        assertThat(isDeleteItem).isTrue();
        verify(itemRepository, times(1)).getItemByUUID(any());
        verify(itemRepository, times(1)).remove(item);
    }

    @Test
    public void testDeleteItemById() {
        Item item = new Item();
        when(itemRepository.findById(any())).thenReturn(item);
        Boolean isDeleteItem = itemService.deleteItem(1L);
        assertThat(isDeleteItem).isTrue();
        verify(itemRepository, times(1)).findById(any());
        verify(itemRepository, times(1)).remove(item);
    }

    private PaginationDTO getPaginationDTO() {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setCurrentPage(1);
        paginationDTO.setElementsPerPage(10);
        return paginationDTO;
    }

    private Pagination getPagination(PaginationDTO paginationDTO) {
        Pagination pagination = new Pagination();
        pagination.setElementsPerPage(paginationDTO.getElementsPerPage());
        pagination.setStartElement((paginationDTO.getCurrentPage() - 1) * paginationDTO.getElementsPerPage());
        return pagination;
    }

}
