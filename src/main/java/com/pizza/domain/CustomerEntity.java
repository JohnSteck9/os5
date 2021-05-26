package com.pizza.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer", schema = "pizza_project")
public class CustomerEntity {
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

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<OrderInfoEntity> orderInfos;


    public CustomerEntity(Integer id, String firstName, String lastName, String phone, String email, Set<OrderInfoEntity> orderInfos) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.orderInfos = orderInfos;
    }

    public CustomerEntity(String firstName, String lastName, String phone, String email, Set<OrderInfoEntity> orderInfos) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.orderInfos = orderInfos;
    }

    public CustomerEntity(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public CustomerEntity() {
    }


    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
        CustomerEntity customer = (CustomerEntity) o;
        return id.equals(customer.id)
                && firstName.equals(customer.firstName)
                && lastName.equals(customer.lastName)
                && phone.equals(customer.phone)
                && email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone, email);
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
