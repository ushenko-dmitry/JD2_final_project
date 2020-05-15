package ru.mail.dimaushenko.service.model;

import java.util.ArrayList;
import java.util.List;

public class ViewBasketDTO {

    private PaginationDTO pagination;
    private List<BasketPreviewDTO> baskets = new ArrayList<>();

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

    public List<BasketPreviewDTO> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<BasketPreviewDTO> baskets) {
        this.baskets = baskets;
    }

}
