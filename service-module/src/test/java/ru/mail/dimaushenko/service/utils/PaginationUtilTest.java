package ru.mail.dimaushenko.service.utils;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import ru.mail.dimaushenko.service.model.PaginationDTO;

public class PaginationUtilTest {

    @Test
    public void testGetStartElement_firstPage() {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setCurrentPage(1);
        paginationDTO.setElementsPerPage(10);

        Integer result = 0;
        Integer startElement = PaginationUtil.getStartElement(paginationDTO);
        Assertions.assertThat(startElement).isEqualByComparingTo(result);
    }

    @Test
    public void testGetStartElement_secondPage() {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setCurrentPage(2);
        paginationDTO.setElementsPerPage(10);

        Integer result = 10;
        Integer startElement = PaginationUtil.getStartElement(paginationDTO);
        Assertions.assertThat(startElement).isEqualByComparingTo(result);
    }

}
