package com.pizza.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "order_status", schema = "pizza_project")
public class OrderStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.EAGER)
    private Set<OrderInfoEntity> orderInfos;


    public OrderStatusEntity(Integer id, String status, Set<OrderInfoEntity> orderInfos) {
        this.id = id;
        this.status = status;
        this.orderInfos = orderInfos;
    }

    public OrderStatusEntity(String status, Set<OrderInfoEntity> orderInfos) {
        this.status = status;
        this.orderInfos = orderInfos;
    }

    public OrderStatusEntity(String status) {
        this.status = status;
    }

    public OrderStatusEntity() {
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
        OrderStatusEntity orderStatus = (OrderStatusEntity) o;
        return id.equals(orderStatus.id)
                && status.equals(orderStatus.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }


    @Override
    public String toString() {
        return "OrderStatusEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
//                ", courier=" + courier +
                '}';
    }
}
