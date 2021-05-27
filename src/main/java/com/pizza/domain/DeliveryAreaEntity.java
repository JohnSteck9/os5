package com.pizza.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "delivery_area", schema = "pizza_project")
public class DeliveryAreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "zone")
    private String zone;
    @Column(name = "delivery_time")
    private String time;

    @OneToMany(mappedBy = "deliveryArea", fetch = FetchType.EAGER)
    private Set<OrderInfoEntity> orderInfos;


    public DeliveryAreaEntity(Integer id, String zone, String time, Set<OrderInfoEntity> orderInfos) {
        this.id = id;
        this.zone = zone;
        this.time = time;
        this.orderInfos = orderInfos;
    }

    public DeliveryAreaEntity(String zone, String time, Set<OrderInfoEntity> orderInfos) {
        this.zone = zone;
        this.time = time;
        this.orderInfos = orderInfos;
    }

    public DeliveryAreaEntity(String zone, String time) {
        this.zone = zone;
        this.time = time;
    }

    public DeliveryAreaEntity(String zone) {
        this.zone = zone;
    }

    public DeliveryAreaEntity() {
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

    public Set<OrderInfoEntity> getOrderInfos() {
        return orderInfos;
    }
    public void setOrderInfos(Set<OrderInfoEntity> orderInfos) {
        this.orderInfos = orderInfos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeliveryAreaEntity deliveryArea = (DeliveryAreaEntity) o;
        return id.equals(deliveryArea.id)
                && zone.equals(deliveryArea.zone)
                && time.equals(deliveryArea.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zone, time);
    }


    @Override
    public String toString() {
        return "DeliveryAreaEntity{" +
                "id=" + id +
                ", zone='" + zone + '\'' +
                ", time='" + time + '\'' +
//                ", courier=" + courier +
                '}';
    }
}
