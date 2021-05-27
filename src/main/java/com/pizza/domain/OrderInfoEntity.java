package com.pizza.domain;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "order_info", schema = "pizza_project")
public class OrderInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "comment")
    private String comment;
    @Column(name = "price_product")
    private Double priceProduct;
    @Column(name = "price_delivery")
    private Double priceDelivery;
    @Column(name = "expected_time")
    private String expectedTime;
    @Column(name = "actual_time")
    private String actualTime;
    @Column(name = "delivery_area_id")
    private Integer deliveryAreaId;
    @Column(name = "order_status_id")
    private Integer orderStatusId;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "courier_id")
    private Integer courierId;


    @ManyToOne
    @JoinColumn (name="delivery_area_id", referencedColumnName = "id", insertable=false, updatable=false)
    private DeliveryAreaEntity deliveryArea;

    @ManyToOne
    @JoinColumn (name="order_status_id", referencedColumnName = "id", insertable=false, updatable=false)
    private OrderStatusEntity orderStatus;

    @ManyToOne
    @JoinColumn (name="customer_id", referencedColumnName = "id", insertable=false, updatable=false)
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn (name="courier_id", referencedColumnName = "id", insertable=false, updatable=false)
    private CourierEntity courier;


    public OrderInfoEntity(Integer id,
                           String comment, Double priceProduct, Double priceDelivery, String expectedTime, String actualTime,
                           Integer deliveryAreaId, Integer orderStatusId, Integer customerId, Integer courierId
    ) {
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

    public OrderInfoEntity(
                           String comment, Double priceProduct, Double priceDelivery, String expectedTime, String actualTime,
                         Integer deliveryAreaId, Integer orderStatusId, Integer customerId, Integer courierId
    ) {
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


    public OrderInfoEntity() {
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
    public void setComment(String comment) {
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



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderInfoEntity orderInfo = (OrderInfoEntity) o;
        return id.equals(orderInfo.id)
                && comment.equals(orderInfo.comment)
                && priceProduct.equals(orderInfo.priceProduct)
                && priceDelivery.equals(orderInfo.priceDelivery)
                && expectedTime.equals(orderInfo.expectedTime)
                && actualTime.equals(orderInfo.actualTime)

                && deliveryAreaId.equals(orderInfo.deliveryAreaId)
                && orderStatusId.equals(orderInfo.orderStatusId)
                && customerId.equals(orderInfo.customerId)
                && courierId.equals(orderInfo.courierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                comment, priceProduct, priceDelivery, expectedTime, actualTime,
                deliveryAreaId, orderStatusId, customerId, courierId
        );
    }

    @Override
    public String toString() {
        return "OrderInfoEntity{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", priceProduct=" + priceProduct +
                ", priceDelivery=" + priceDelivery +
                ", expectedTime='" + expectedTime + '\'' +
                ", actualTime='" + actualTime + '\'' +
                ", deliveryAreaId=" + deliveryAreaId +
                ", orderStatusId=" + orderStatusId +
                ", customerId=" + customerId +
                ", courierId=" + courierId +
                '}';
    }
}
