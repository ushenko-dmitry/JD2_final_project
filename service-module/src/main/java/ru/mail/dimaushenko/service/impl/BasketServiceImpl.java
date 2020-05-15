package ru.mail.dimaushenko.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.BasketRepository;
import static ru.mail.dimaushenko.repository.constants.SortOrderEnum.DESC;
import ru.mail.dimaushenko.repository.model.Basket;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.BasketService;
import ru.mail.dimaushenko.service.converter.BasketConverter;
import ru.mail.dimaushenko.service.converter.BasketPreviewConverter;
import ru.mail.dimaushenko.service.converter.ConverterFacade;
import ru.mail.dimaushenko.service.converter.PaginationConverter;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.model.BasketDTO;
import ru.mail.dimaushenko.service.model.BasketPreviewDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

@Service
@Transactional
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ConverterFacade converterFacade;

    public BasketServiceImpl(
            BasketRepository basketRepository,
            ConverterFacade converterFacade
    ) {
        this.basketRepository = basketRepository;
        this.converterFacade = converterFacade;
    }

    @Override
    public List<BasketPreviewDTO> getPreviewBaskets(UserDTO userDTO, PaginationDTO paginationDTO) {
        PaginationConverter paginationConverter = converterFacade.getPaginationConverter();
        Pagination pagination = paginationConverter.getObjectFromDTO(paginationDTO);
        UserConverter userConverter = converterFacade.getUserConverter();
        User user = userConverter.getObjectFromDTO(userDTO);
        List<Basket> baskets = new ArrayList<>();
        switch (userDTO.getRole()) {
            case CUSTOMER_USER:
                baskets = basketRepository.getBasketsForCustomerOrderedByDate(user, pagination, DESC);
                break;
            case SALE_USER:
                baskets = basketRepository.getBasketsForSalerOrderedByDate(user, pagination, DESC);
                break;
        }
        BasketPreviewConverter basketPreviewConverter = converterFacade.getBasketPreviewConverter();
        return basketPreviewConverter.getDTOFromObject(baskets);
    }

    @Override
    public Integer getAmountBaskets(UserDTO userDTO) {
        UserConverter userConverter = converterFacade.getUserConverter();
        User user = userConverter.getObjectFromDTO(userDTO);
        switch (userDTO.getRole()) {
            case CUSTOMER_USER:
                return basketRepository.getAmountBasketsForCustomer(user);
            case SALE_USER:
                return basketRepository.getAmountBasketsForSaler(user);
            default:
                return 0;
        }
    }

    @Override
    public BasketDTO getBasket(Long id) {
        Basket basket = basketRepository.findById(id);
        BasketConverter basketConverter = converterFacade.getBasketConverter();
        return basketConverter.getDTOFromObject(basket);
    }

    @Override
    public void updateBasket(BasketDTO basketDTO) {
        BasketConverter basketConverter = converterFacade.getBasketConverter();
        Basket updatedBasket = basketConverter.getObjectFromDTO(basketDTO);
        Basket basket = basketRepository.findById(basketDTO.getId());
        basket.setOrderStatus(updatedBasket.getOrderStatus());
    }

    @Override
    public List<BasketDTO> getBaskets() {
        BasketConverter basketConverter = converterFacade.getBasketConverter();
        List<Basket> baskets = basketRepository.findAll();
        return basketConverter.getDTOFromObject(baskets);
    }

}
