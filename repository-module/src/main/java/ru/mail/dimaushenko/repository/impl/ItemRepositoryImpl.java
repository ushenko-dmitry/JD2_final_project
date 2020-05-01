package ru.mail.dimaushenko.repository.impl;

import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.mail.dimaushenko.repository.ItemRepository;
import ru.mail.dimaushenko.repository.constants.SortOrderEnum;
import static ru.mail.dimaushenko.repository.constants.SortOrderEnum.ASC;
import static ru.mail.dimaushenko.repository.constants.SortOrderEnum.DESC;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.Pagination;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepository {

    @Override
    public List<Item> getItems(Pagination pagination, SortOrderEnum sort) {
        String queryString = "FROM Item i ORDER BY i.name";
        switch (sort) {
            case ASC:
                queryString += " asc";
                break;
            case DESC:
                queryString += " desc";
                break;
        }
        Query query = entityManager.createQuery(queryString);
        query.setFirstResult(pagination.getStartElement());
        query.setMaxResults(pagination.getElementsPerPage());
        return query.getResultList();
    }

    @Override
    public Item getItemByUUID(UUID uuid) {
        String queryString = "FROM Item i "
                + "WHERE i.uuid=:uuid";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("uuid", uuid.toString());
        return (Item) query.getSingleResult();
    }

}
