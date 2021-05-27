package com.pizza.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "courier_status", schema = "pizza_project")
public class CourierStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "courierStatus", fetch = FetchType.EAGER)
    private Set<CourierEntity> couriers;


    public CourierStatusEntity(Integer id, String status, Set<CourierEntity> couriers) {
        this.id = id;
        this.status = status;
        this.couriers = couriers;
    }

    public CourierStatusEntity(String status, Set<CourierEntity> couriers) {
        this.status = status;
        this.couriers = couriers;
    }

    public CourierStatusEntity(String status) {
        this.status = status;
    }

    public CourierStatusEntity() {
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

    public Set<CourierEntity> getCouriers() {
        return couriers;
    }
    public void setCouriers(Set<CourierEntity> couriers) {
        this.couriers = couriers;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourierStatusEntity courierStatus = (CourierStatusEntity) o;
        return id.equals(courierStatus.id)
                && status.equals(courierStatus.status);
    }

    @Override
    public String toString() {
        return "CourierStatusEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
//                ", courier=" + couriers +
                '}';
    }
}
