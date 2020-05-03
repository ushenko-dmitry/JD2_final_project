package ru.mail.dimaushenko.service.converter.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.Basket;
import ru.mail.dimaushenko.service.converter.BasketPreviewConverter;
import ru.mail.dimaushenko.service.converter.OrderStatusConverter;
import ru.mail.dimaushenko.service.converter.OrderedItemConverter;
import ru.mail.dimaushenko.service.model.BasketPreviewDTO;
import ru.mail.dimaushenko.service.model.OrderStatusEnumDTO;
import ru.mail.dimaushenko.service.model.OrderedItemDTO;

@Component
public class BasketPreviewConverterImpl implements BasketPreviewConverter {

    private final OrderStatusConverter orderStatusConverter;
    private final OrderedItemConverter orderedItemConverter;

    public BasketPreviewConverterImpl(
            OrderStatusConverter orderStatusConverter,
            OrderedItemConverter orderedItemConverter
    ) {
        this.orderStatusConverter = orderStatusConverter;
        this.orderedItemConverter = orderedItemConverter;
    }

    @Override
    public BasketPreviewDTO getDTOFromObject(Basket basket) {
        BasketPreviewDTO basketPreviewDTO = new BasketPreviewDTO();
        basketPreviewDTO.setId(basket.getId());

        OrderStatusEnumDTO orderStatusDTO = orderStatusConverter.getDTOFromObject(basket.getOrderStatus());
        basketPreviewDTO.setOrderStatus(orderStatusDTO);

        OrderedItemDTO orderedItem = orderedItemConverter.getDTOFromObject(basket.getOrderedItem());
        basketPreviewDTO.setOrderedItem(orderedItem);
        return basketPreviewDTO;
    }

    @Override
    public List<BasketPreviewDTO> getDTOFromObject(List<Basket> baskets) {
        List<BasketPreviewDTO> basketPreviewDTOList = new ArrayList<>();
        for (Basket basket : baskets) {
            basketPreviewDTOList.add(getDTOFromObject(basket));
        }
        return basketPreviewDTOList;
    }

    @Override
    public Basket getObjectFromDTO(BasketPreviewDTO modelDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Basket> getObjectFromDTO(List<BasketPreviewDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
