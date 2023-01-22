package com.inventory.MedationInventory.service;

import com.inventory.MedationInventory.config.Response;
import com.inventory.MedationInventory.dto.MeditionDto;
import com.inventory.MedationInventory.entity.Medition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MeditionService {

    Response addMedition(MeditionDto meditionDto);

    Response getById(Integer mId);

    List<Medition> getAll(MeditionDto meditionDto);

    Response getByMeditionName(String meditionName);
}
