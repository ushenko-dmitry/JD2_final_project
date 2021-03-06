package ru.mail.dimaushenko.service.converter.impl;

import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.service.converter.AddBasketConverter;
import ru.mail.dimaushenko.service.converter.AddItemConverter;
import ru.mail.dimaushenko.service.converter.ArticleConverter;
import ru.mail.dimaushenko.service.converter.BasketConverter;
import ru.mail.dimaushenko.service.converter.BasketPreviewConverter;
import ru.mail.dimaushenko.service.converter.CommentConverter;
import ru.mail.dimaushenko.service.converter.ConverterFacade;
import ru.mail.dimaushenko.service.converter.ItemConverter;
import ru.mail.dimaushenko.service.converter.ItemDetailsConverter;
import ru.mail.dimaushenko.service.converter.ItemPreviewConverter;
import ru.mail.dimaushenko.service.converter.ItemXMLConverter;
import ru.mail.dimaushenko.service.converter.OrderedItemConverter;
import ru.mail.dimaushenko.service.converter.PaginationConverter;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.converter.UserDetailsConverter;
import ru.mail.dimaushenko.service.converter.UserProfileConverter;
import ru.mail.dimaushenko.service.converter.UserRoleConverter;

@Component
public class ConverterFacadeImpl implements ConverterFacade {

    private final UserConverter userConverter;
    private final CommentConverter commentConverter;
    private final UserDetailsConverter userDetailsConverter;
    private final UserRoleConverter userRoleConverter;
    private final PaginationConverter paginationConverter;
    private final UserProfileConverter userProfileConverter;
    private final ArticleConverter articleConverter;
    private final ItemConverter itemConverter;
    private final ItemDetailsConverter itemDetailsConverter;
    private final ItemPreviewConverter itemPreviewConverter;
    private final ItemXMLConverter itemXMLConverter;
    private final AddItemConverter addItemConverter;
    private final BasketConverter basketConverter;
    private final AddBasketConverter addBasketConverter;
    private final BasketPreviewConverter basketPreviewConverter;
    private final OrderedItemConverter orderedItemConverter;

    public ConverterFacadeImpl(
            UserConverter userConverter,
            CommentConverter commentConverter,
            UserDetailsConverter userDetailsConverter,
            UserRoleConverter userRoleConverter,
            PaginationConverter paginationConverter,
            UserProfileConverter userProfileConverter,
            ArticleConverter articleConverter,
            ItemConverter itemConverter,
            ItemDetailsConverter itemDetailsConverter,
            ItemPreviewConverter itemPreviewConverter,
            ItemXMLConverter itemXMLConverter,
            AddItemConverter addItemConverter,
            BasketConverter basketConverter,
            AddBasketConverter addBasketConverter,
            BasketPreviewConverter basketPreviewConverter,
            OrderedItemConverter orderedItemConverter
    ) {
        this.userConverter = userConverter;
        this.commentConverter = commentConverter;
        this.userDetailsConverter = userDetailsConverter;
        this.userRoleConverter = userRoleConverter;
        this.paginationConverter = paginationConverter;
        this.userProfileConverter = userProfileConverter;
        this.articleConverter = articleConverter;
        this.itemConverter = itemConverter;
        this.itemDetailsConverter = itemDetailsConverter;
        this.itemPreviewConverter = itemPreviewConverter;
        this.itemXMLConverter = itemXMLConverter;
        this.addItemConverter = addItemConverter;
        this.basketConverter = basketConverter;
        this.addBasketConverter = addBasketConverter;
        this.basketPreviewConverter = basketPreviewConverter;
        this.orderedItemConverter = orderedItemConverter;
    }

    @Override
    public UserConverter getUserConverter() {
        return userConverter;
    }

    @Override
    public CommentConverter getCommentConverter() {
        return commentConverter;
    }

    @Override
    public UserDetailsConverter getUserDetailsConverter() {
        return userDetailsConverter;
    }

    @Override
    public UserRoleConverter getUserRoleConverter() {
        return userRoleConverter;
    }

    @Override
    public PaginationConverter getPaginationConverter() {
        return paginationConverter;
    }

    @Override
    public UserProfileConverter getUserProfileConverter() {
        return userProfileConverter;
    }

    @Override
    public ArticleConverter getArticleConverter() {
        return articleConverter;
    }

    @Override
    public ItemConverter getItemConverter() {
        return itemConverter;
    }

    @Override
    public ItemDetailsConverter getItemDetailsConverter() {
        return itemDetailsConverter;
    }

    @Override
    public ItemPreviewConverter getItemPreviewConverter() {
        return itemPreviewConverter;
    }

    @Override
    public AddItemConverter getAddItemConverter() {
        return addItemConverter;
    }

    @Override
    public ItemXMLConverter getItemXMLConverter() {
        return itemXMLConverter;
    }

    @Override
    public BasketConverter getBasketConverter() {
        return basketConverter;
    }

    @Override
    public AddBasketConverter getAddBasketConverter() {
        return addBasketConverter;
    }

    @Override
    public BasketPreviewConverter getBasketPreviewConverter() {
        return basketPreviewConverter;
    }

    @Override
    public OrderedItemConverter getOrderedItemConverter() {
        return orderedItemConverter;
    }

}
