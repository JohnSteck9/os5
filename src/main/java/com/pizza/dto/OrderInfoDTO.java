package com.pizza.dto;

public class OrderInfoDTO {

    private Integer id;
    private String comment;
    private Double priceProduct;
    private Double priceDelivery;
    private String expectedTime;
    private String actualTime;
    private Integer deliveryAreaId;
    private Integer orderStatusId;
    private Integer customerId;
    private Integer courierId;


    public OrderInfoDTO(Integer id, String comment, Double priceProduct, Double priceDelivery, String expectedTime,
                        String actualTime, Integer deliveryAreaId, Integer orderStatusId, Integer customerId,
                        Integer courierId) {
        this.id = id;
        this.comment = comment;
        this.priceProduct = priceProduct;
        this.priceDelivery = priceDelivery;
        this.expectedTime = expectedTime;
        this.actualTime = actualTime;
        this.deliveryAreaId = deliveryAreaId;
        this.orderStatusId = orderStatusId;
        this.customerId = customerId;
        this.courierId = courierId;
    }
    public OrderInfoDTO(Integer id, String comment, Double priceProduct, Double priceDelivery, String expectedTime,
                        String actualTime) {
        this.id = id;
        this.comment = comment;
        this.priceProduct = priceProduct;
        this.priceDelivery = priceDelivery;
        this.expectedTime = expectedTime;
        this.actualTime = actualTime;
    }
    public OrderInfoDTO() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String orderComment) {
        this.comment = comment;
    }

    public Double getPriceProduct() {
        return priceProduct;
    }
    public void setPriceProduct(Double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Double getPriceDelivery() {
        return priceDelivery;
    }
    public void setPriceDelivery(Double priceDelivery) {
        this.priceDelivery = priceDelivery;
    }

    public String getExpectedTime() {
        return expectedTime;
    }
    public void setExpectedTime(String expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getActualTime() {
        return actualTime;
    }
    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public Integer getDeliveryAreaId() {
        return deliveryAreaId;
    }
    public void setDeliveryAreaId(Integer deliveryAreaId) { this.deliveryAreaId = deliveryAreaId; }

    public Integer getOrderStatusId() {
        return orderStatusId;
    }
    public void setOrderStatusId(Integer orderStatusId) { this.orderStatusId = orderStatusId; }

    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public Integer getCourierId() {
        return courierId;
    }
    public void setCourierId(Integer courierId) { this.courierId = courierId; }

}
