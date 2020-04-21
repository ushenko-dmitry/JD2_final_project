package ru.mail.dimaushenko.service.converter.impl;

import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.service.converter.ArticleConverter;
import ru.mail.dimaushenko.service.converter.CommentConverter;
import ru.mail.dimaushenko.service.converter.ConverterFacade;
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

    public ConverterFacadeImpl(
            UserConverter userConverter,
            CommentConverter commentConverter,
            UserDetailsConverter userDetailsConverter,
            UserRoleConverter userRoleConverter,
            PaginationConverter paginationConverter,
            UserProfileConverter userProfileConverter,
            ArticleConverter articleConverter
    ) {
        this.userConverter = userConverter;
        this.commentConverter = commentConverter;
        this.userDetailsConverter = userDetailsConverter;
        this.userRoleConverter = userRoleConverter;
        this.paginationConverter = paginationConverter;
        this.userProfileConverter = userProfileConverter;
        this.articleConverter = articleConverter;
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

}
