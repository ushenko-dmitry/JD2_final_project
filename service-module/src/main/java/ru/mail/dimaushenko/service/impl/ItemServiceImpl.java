package ru.mail.dimaushenko.service.impl;

import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.ItemRepository;
import ru.mail.dimaushenko.service.ItemService;
import ru.mail.dimaushenko.service.converter.ConverterFacade;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ConverterFacade converterFacade;

    public ItemServiceImpl(
            ItemRepository itemRepository,
            ConverterFacade converterFacade
    ) {
        this.itemRepository = itemRepository;
        this.converterFacade = converterFacade;
    }

}
