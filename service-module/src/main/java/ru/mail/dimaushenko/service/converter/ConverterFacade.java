package ru.mail.dimaushenko.service.converter;

public interface ConverterFacade {

    UserConverter getUserConverter();

    CommentConverter getCommentConverter();

    UserDetailsConverter getUserDetailsConverter();

    UserRoleConverter getUserRoleConverter();

    PaginationConverter getPaginationConverter();

    UserProfileConverter getUserProfileConverter();

    ArticleConverter getArticleConverter();

    ItemConverter getItemConverter();

    ItemDetailsConverter getItemDetailsConverter();

    ItemPreviewConverter getItemPreviewConverter();

    ItemXMLConverter getItemXMLConverter();

    AddItemConverter getAddItemConverter();

    BasketConverter getBasketConverter();

    AddBasketConverter getAddBasketConverter();

    BasketPreviewConverter getBasketPreviewConverter();

    OrderedItemConverter getOrderedItemConverter();

}
