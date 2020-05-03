package ru.mail.dimaushenko.service.converter.impl;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import static ru.mail.dimaushenko.repository.constants.OrderStatusEnum.NEW;
import ru.mail.dimaushenko.repository.model.Basket;
import ru.mail.dimaushenko.repository.model.OrderedItem;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.converter.AddBasketConverter;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.model.AddBasketDTO;
import ru.mail.dimaushenko.service.model.ItemDTO;

@Component
public class AddBasketConverterImpl implements AddBasketConverter {

    private final UserConverter userConverter;

    public AddBasketConverterImpl(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public AddBasketDTO getDTOFromObject(Basket model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AddBasketDTO> getDTOFromObject(List<Basket> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Basket getObjectFromDTO(AddBasketDTO addBasketDTO) {
        Basket basket = new Basket();
        basket.setOrderStatus(NEW);
        basket.setCreationDate(new Date(System.currentTimeMillis()));

        OrderedItem orderedItem = new OrderedItem();
        orderedItem.setAmount(addBasketDTO.getAmount());
        orderedItem.setBasket(basket);

        ItemDTO item = addBasketDTO.getItem();
        orderedItem.setName(item.getName());
        orderedItem.setPrice(item.getPrice());
        User userSaler = userConverter.getObjectFromDTO(item.getUser());
        orderedItem.setUser(userSaler);
        basket.setOrderedItem(orderedItem);
        User userCustomer = userConverter.getObjectFromDTO(addBasketDTO.getUser());
        basket.setUser(userCustomer);
        return basket;
    }

    @Override
    public List<Basket> getObjectFromDTO(List<AddBasketDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
