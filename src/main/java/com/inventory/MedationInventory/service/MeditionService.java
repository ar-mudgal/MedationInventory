package com.inventory.MedationInventory.service;

import com.inventory.MedationInventory.config.Response;
import com.inventory.MedationInventory.dto.MeditionDto;
import com.inventory.MedationInventory.dto.MeditionListDto;
import com.inventory.MedationInventory.entity.Medition;

import java.util.List;

public interface MeditionService {

    Response addMedition(MeditionDto meditionDto);

    Response getById(Integer maId);

    List<Medition> getAll(MeditionDto meditionDto);

    Response getByMeditionName(String meditionName);

    Response delete(Integer MaId);

    Response updateMedition(MeditionDto meditionDto) throws Exception;

    Response getMeditionBySaltAndBatchNo(String salt, String batchNo);

     Response addaddMeditionToBegB(MeditionListDto meditionListto);

     Response getExpiryMedition(String fromDate, String toDate);

     Response removeMeditionFromBag();

}
