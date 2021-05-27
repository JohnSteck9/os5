package com.pizza.repository;

import com.pizza.domain.CourierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, Integer> {

}
