package com.inventory.MedationInventory.repository;

import com.inventory.MedationInventory.entity.Medition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeditionRepository extends JpaRepository<Medition,Integer> {
    Optional<Medition> findByMeditionName(String meditionName);
}
