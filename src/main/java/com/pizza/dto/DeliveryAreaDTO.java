package com.pizza.dto;

public class DeliveryAreaDTO {
    private Integer id;
    private String zone;
    private String time;


    public DeliveryAreaDTO(Integer id, String zone, String time) {
        this.id = id;
        this.zone = zone;
        this.time = time;
    }

    public DeliveryAreaDTO() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getZone() {
        return zone;
    }
    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getDeliveryTime() {
        return time;
    }
    public void setDeliveryTime(String time) {
        this.time = time;
    }

}
