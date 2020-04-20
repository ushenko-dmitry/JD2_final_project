package ru.mail.dimaushenko.service.converter.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.service.converter.PaginationConverter;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.utils.PaginationUtil;

@Component
public class PaginationConverterImpl implements PaginationConverter {

    private final PaginationUtil paginationUtil;

    public PaginationConverterImpl(PaginationUtil paginationUtil) {
        this.paginationUtil = paginationUtil;
    }

    @Override
    public PaginationDTO getDTOFromObject(Pagination model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaginationDTO> getDTOFromObject(List<Pagination> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pagination getObjectFromDTO(PaginationDTO paginationDTO) {
        Pagination pagination = new Pagination();
        Integer startElement = paginationUtil.getStartElement(paginationDTO);
        pagination.setStartElement(startElement);
        pagination.setElementsPerPage(paginationDTO.getElementsPerPage());
        return pagination;
    }

    @Override
    public List<Pagination> getObjectFromDTO(List<PaginationDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
