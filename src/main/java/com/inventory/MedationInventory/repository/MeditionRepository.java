package com.inventory.MedationInventory.repository;

import com.inventory.MedationInventory.dto.MeditionRequest;
import com.inventory.MedationInventory.entity.Medition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeditionRepository extends JpaRepository<Medition,Integer> {
    Optional<Medition> getByMeditionName(String meditionName);

    Optional<List<Medition>> findByMeditionName(String meditionName);

//    List<MeditionRequest> getByMeditionName(String meditionName);

    void deleteByMaId(Medition meditionName);

//    @Query(value = "select * from medition_table where salt as salt or batch_no as batch_no",nativeQuery = true)
    List<Medition> findBySaltOrBatchNo(String salt, String batchNo);

//    @Query(value = "select * from medition_table mt where CAST(mt.expire_date as DATE) between ?1 AND ?2",nativeQuery = true)
//    select * from medition_table mt where expire_date between '2023-01-24' AND '2023-02-01';
    @Query(value = " select * from medition_table mt where mt.expire_date between ?1 AND ?2",nativeQuery = true)
//    @Query(value = "select * from medition_table mt where CAST(mt.expire_date as DATE) between ?1 AND ?2",nativeQuery = true)
    List<Medition> findByfromDateToExpDate(LocalDate fromDate, LocalDate toDate);


//    Optional<List<Medition>> findBySaltOrBatchNo(String salt, String batchNo);
//    Optional<List<Medition>> findBySaltAndBatchNo(String salt, String batchNo);


//    List<Medition> findBySaltOrBatchNoNotNull(String salt, String batchNo);
}

