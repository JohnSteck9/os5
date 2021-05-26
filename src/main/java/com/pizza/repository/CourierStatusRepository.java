package com.pizza.repository;

import com.pizza.domain.CourierStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourierStatusRepository extends JpaRepository<CourierStatusEntity, Integer> {

}