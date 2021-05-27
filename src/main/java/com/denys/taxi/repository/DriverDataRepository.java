package com.denys.taxi.repository;

import com.denys.taxi.domain.DriverData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverDataRepository extends JpaRepository<DriverData, Integer> {
}