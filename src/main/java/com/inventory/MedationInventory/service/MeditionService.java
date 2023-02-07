package com.inventory.MedationInventory.service;

import com.inventory.MedationInventory.config.Response;
import com.inventory.MedationInventory.dto.MeditionDto;
import com.inventory.MedationInventory.entity.Medition;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface MeditionService {

    Response addMedition(MeditionDto meditionDto);

    Response getById(Integer maId);

    List<Medition> getAll(MeditionDto meditionDto);

    Response getByMeditionName(String meditionName);

    Response delete(Integer MaId);

    Response updateMedition(MeditionDto meditionDto) throws Exception;

//    Response getMeditionExpiredDate(LocalDate mfgDate, LocalDate expireDate);

    Response getMeditionExpiredDate(MeditionDto meditionDto);
}
