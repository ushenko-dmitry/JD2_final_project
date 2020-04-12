package ru.mail.dimaushenko.service.model;

import java.util.ArrayList;
import java.util.List;

public class PaginationDTO {

    private Integer currentPage;
    private Integer elementsPerPage;
    private List<Integer> pageNumbers = new ArrayList<>();

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getElementsPerPage() {
        return elementsPerPage;
    }

    public void setElementsPerPage(Integer elementsPerPage) {
        this.elementsPerPage = elementsPerPage;
    }

    public List<Integer> getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(List<Integer> pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

}
