package com.denys.taxi.repository;

import com.denys.taxi.domain.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Integer> {
}