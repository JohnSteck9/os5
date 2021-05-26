package com.pizza.repository;

import com.pizza.domain.DeliveryAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeliveryAreaRepository extends JpaRepository<DeliveryAreaEntity, Integer> {

}