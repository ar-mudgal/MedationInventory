package com.inventory.MedationInventory.repository;

import com.inventory.MedationInventory.entity.Medition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeditionRepository extends JpaRepository<Medition,Integer> {
    Optional<Medition> findByMeditionName(String meditionName);

    void deleteByMaId(Medition meditionName);

//    @Query(value = "select * from medition_table where salt as salt or batch_no as batch_no",nativeQuery = true)
    List<Medition> findBySaltOrBatchNo(String salt, String batchNo);

//    Optional<List<Medition>> findBySaltOrBatchNo(String salt, String batchNo);
//    Optional<List<Medition>> findBySaltAndBatchNo(String salt, String batchNo);


//    List<Medition> findBySaltOrBatchNoNotNull(String salt, String batchNo);
}

