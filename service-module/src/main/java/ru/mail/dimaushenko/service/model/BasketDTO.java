package ru.mail.dimaushenko.service.model;

public class BasketDTO {

    private Long id;
    private Long userId;
    private String userPhone;
    private OrderStatusEnumDTO orderStatus;
    private OrderedItemDTO orderedItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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
