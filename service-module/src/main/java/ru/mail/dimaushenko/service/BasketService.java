package ru.mail.dimaushenko.service;

import java.util.List;
import ru.mail.dimaushenko.service.model.BasketDTO;
import ru.mail.dimaushenko.service.model.BasketPreviewDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

public interface BasketService {

    List<BasketDTO> getBaskets();

    void updateBasket(BasketDTO basket);

    BasketDTO getBasket(Long id);

    List<BasketPreviewDTO> getPreviewBaskets(UserDTO user, PaginationDTO pagination);

    Integer getAmountBaskets(UserDTO user);

}
