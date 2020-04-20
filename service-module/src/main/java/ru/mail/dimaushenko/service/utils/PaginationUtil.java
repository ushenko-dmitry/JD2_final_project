package ru.mail.dimaushenko.service.utils;

import ru.mail.dimaushenko.service.model.PaginationDTO;

public interface PaginationUtil {

    Integer getStartElement(PaginationDTO paginationDTO);
    
}
