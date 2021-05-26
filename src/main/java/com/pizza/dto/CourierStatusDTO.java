package com.pizza.dto;

public class CourierStatusDTO {
    private Integer id;
    private String status;

    public CourierStatusDTO(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public CourierStatusDTO() {
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
