package ru.mail.dimaushenko.service.model;

import java.util.List;

public class ViewItemsDTO {

    private PaginationDTO pagination;
    private List<ItemPreviewDTO> items;

    public PaginationDTO getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDTO pagination) {
        this.pagination = pagination;
    }

    public List<ItemPreviewDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemPreviewDTO> items) {
        this.items = items;
    }

}
