package ru.mail.dimaushenko.repository;

import java.util.List;
import ru.mail.dimaushenko.repository.constants.SortOrderEnum;
import ru.mail.dimaushenko.repository.model.Basket;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.User;

public interface BasketRepository extends GenericRepository<Long, Basket> {

    Integer getAmountBasketsForCustomer(User user);

    Integer getAmountBasketsForSaler(User user);

    List<Basket> getBasketsForCustomerOrderedByDate(User user, Pagination pagination, SortOrderEnum sortOrderEnum);

    List<Basket> getBasketsForSalerOrderedByDate(User user, Pagination pagination, SortOrderEnum sortOrderEnum);

    List<Basket> getBasketsOrderedByDate(Pagination pagination, SortOrderEnum sortOrderEnum);

}
