package com.pizza.repository;

import com.pizza.domain.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Integer> {

}