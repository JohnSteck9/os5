package com.pizza.dto;

public class OrderStatusDTO {
    private Integer id;
    private String status;

    public OrderStatusDTO(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public OrderStatusDTO() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
