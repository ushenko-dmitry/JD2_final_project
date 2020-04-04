package ru.mail.dimaushenko.service.impl;

import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.service.ConverterFacade;
import ru.mail.dimaushenko.service.UserConverter;

@Component
public class ConverterFacadeImpl implements ConverterFacade {

    private final UserConverter userConverter;

    public ConverterFacadeImpl(
            UserConverter userConverter
    ) {
        this.userConverter = userConverter;
    }

    @Override
    public UserConverter getUserConverter() {
        return userConverter;
    }

}
