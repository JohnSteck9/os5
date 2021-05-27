package com.denys.taxi.repository;

import com.denys.taxi.domain.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto, Integer> {
}