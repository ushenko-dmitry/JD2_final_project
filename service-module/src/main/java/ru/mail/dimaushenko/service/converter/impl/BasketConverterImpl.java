package ru.mail.dimaushenko.service.converter.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.Basket;
import ru.mail.dimaushenko.service.converter.BasketConverter;
import ru.mail.dimaushenko.service.converter.OrderStatusConverter;
import ru.mail.dimaushenko.service.converter.OrderedItemConverter;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.model.BasketDTO;
import ru.mail.dimaushenko.service.model.OrderStatusEnumDTO;
import ru.mail.dimaushenko.service.model.OrderedItemDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

@Component
public class BasketConverterImpl implements BasketConverter {

    private final OrderStatusConverter orderStatusConverter;
    private final OrderedItemConverter orderedItemConverter;
    private final UserConverter userConverter;

    public BasketConverterImpl(OrderStatusConverter orderStatusConverter, OrderedItemConverter orderedItemConverter, UserConverter userConverter) {
        this.orderStatusConverter = orderStatusConverter;
        this.orderedItemConverter = orderedItemConverter;
        this.userConverter = userConverter;
    }

    @Override
    public BasketDTO getDTOFromObject(Basket model) {
        BasketDTO basketDTO = new BasketDTO();
        basketDTO.setId(model.getId());
        OrderStatusEnumDTO orderStatusDTO = orderStatusConverter.getDTOFromObject(model.getOrderStatus());
        basketDTO.setOrderStatus(orderStatusDTO);
        OrderedItemDTO orderedItemDTO = orderedItemConverter.getDTOFromObject(model.getOrderedItem());
        basketDTO.setOrderedItem(orderedItemDTO);
        UserDTO userDTO = userConverter.getDTOFromObject(model.getUser());
        basketDTO.setUserId(userDTO.getId());
        basketDTO.setUserPhone(userDTO.getUserDetails().getPhone());
        return basketDTO;
    }

    @Override
    public List<BasketDTO> getDTOFromObject(List<Basket> baskets) {
        List<BasketDTO> basketDTOList = new ArrayList<>();
        for (Basket basket : baskets) {
            basketDTOList.add(getDTOFromObject(basket));
        }
        return basketDTOList;
    }

    @Override
    public Basket getObjectFromDTO(BasketDTO modelDTO) {
        Basket basket = new Basket();
        basket.setId(modelDTO.getId());
        basket.setOrderStatus(orderStatusConverter.getObjectFromDTO(modelDTO.getOrderStatus()));
        return basket;
    }

    @Override
    public List<Basket> getObjectFromDTO(List<BasketDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
