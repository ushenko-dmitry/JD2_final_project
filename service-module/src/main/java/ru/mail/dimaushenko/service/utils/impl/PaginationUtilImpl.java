package ru.mail.dimaushenko.service.utils.impl;

import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.utils.PaginationUtil;

@Component
public class PaginationUtilImpl implements PaginationUtil {

    @Override
    public Integer getStartElement(PaginationDTO paginationDTO) {
        return (paginationDTO.getCurrentPage() - 1) * paginationDTO.getElementsPerPage();
    }

}
