package com.pizza.repository;

import com.pizza.domain.OrderInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity, Integer> {

}