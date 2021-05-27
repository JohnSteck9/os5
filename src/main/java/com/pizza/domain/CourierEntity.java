package com.pizza.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "courier", schema = "pizza_project")
public class CourierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "courier_status_id")
    private Integer courierStatusId;

    @ManyToOne
    @JoinColumn (name="courier_status_id", referencedColumnName = "id", insertable=false, updatable=false)
    private CourierStatusEntity courierStatus;

    @OneToMany(mappedBy = "courier", fetch = FetchType.EAGER)
    private Set<OrderInfoEntity> orderInfos;


    public CourierEntity(Integer id, CourierStatusEntity courierStatus, String firstName, String lastName,
                         String phone, String email, Set<OrderInfoEntity> orderInfos) {
        this.id = id;
        this.courierStatus = courierStatus;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.orderInfos = orderInfos;
    }

    public CourierEntity(String firstName, String lastName, String phone, String email,
                         Integer courierStatusId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.courierStatusId = courierStatusId;
    }

    public CourierEntity(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public CourierEntity() {
    }


    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public CourierStatusEntity getCourierStatus() {
        return courierStatus;
    }
    public void setCourierStatus(CourierStatusEntity courierStatus) {
        this.courierStatus = courierStatus;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public Integer getCourierStatusId() {
        return courierStatusId;
    }
    public void setCourierStatusId(int courierStatusId) {
        this.courierStatusId = courierStatusId;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, courierStatus, firstName, lastName, phone, email, courierStatusId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourierEntity courier = (CourierEntity) o;
        return id.equals(courier.id)
                && firstName.equals(courier.firstName)
                && courierStatus.equals(courier.courierStatus)
                && lastName.equals(courier.lastName)
                && phone.equals(courier.phone)
                && email.equals(courier.email)
                && courierStatusId.equals(courier.courierStatusId);
    }

    @Override
    public String toString() {
        return "CourierEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", courierStatusId='" + courierStatus.getId() + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", courierStatusId=" + courierStatusId +
//                ", orderInfo=" + orderInfo +
                '}';
    }


}
