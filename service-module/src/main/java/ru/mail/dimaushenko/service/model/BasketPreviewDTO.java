package ru.mail.dimaushenko.service.model;

public class BasketPreviewDTO {

    private Long id;
    private OrderStatusEnumDTO orderStatus;
    private OrderedItemDTO orderedItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatusEnumDTO getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnumDTO orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderedItemDTO getOrderedItem() {
        return orderedItem;
    }

    public void setOrderedItem(OrderedItemDTO orderedItem) {
        this.orderedItem = orderedItem;
    }

}
