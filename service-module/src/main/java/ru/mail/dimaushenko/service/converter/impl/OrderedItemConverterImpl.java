package ru.mail.dimaushenko.service.converter.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.OrderedItem;
import ru.mail.dimaushenko.service.converter.OrderedItemConverter;
import ru.mail.dimaushenko.service.model.OrderedItemDTO;

@Component
public class OrderedItemConverterImpl implements OrderedItemConverter {

    @Override
    public OrderedItemDTO getDTOFromObject(OrderedItem orderedItem) {
        OrderedItemDTO orderedItemDTO = new OrderedItemDTO();
        orderedItemDTO.setId(orderedItem.getBasketId());
        orderedItemDTO.setName(orderedItem.getName());
        orderedItemDTO.setPrice(orderedItem.getPrice());
        orderedItemDTO.setAmount(orderedItem.getAmount());
        return orderedItemDTO;
    }

    @Override
    public List<OrderedItemDTO> getDTOFromObject(List<OrderedItem> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderedItem getObjectFromDTO(OrderedItemDTO modelDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderedItem> getObjectFromDTO(List<OrderedItemDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
