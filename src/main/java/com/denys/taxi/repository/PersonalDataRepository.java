package com.denys.taxi.repository;

import com.denys.taxi.domain.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
}