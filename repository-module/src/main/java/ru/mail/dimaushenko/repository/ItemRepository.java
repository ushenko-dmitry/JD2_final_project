package ru.mail.dimaushenko.repository;

import java.util.List;
import java.util.UUID;
import ru.mail.dimaushenko.repository.constants.SortOrderEnum;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.Pagination;

public interface ItemRepository extends GenericRepository<Long, Item> {

    List<Item> getItems(Pagination pagination, SortOrderEnum sort);

    Item getItemByUUID(UUID uuid);

}
