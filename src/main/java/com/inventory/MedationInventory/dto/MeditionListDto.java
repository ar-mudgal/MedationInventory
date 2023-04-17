package com.inventory.MedationInventory.dto;

import com.inventory.MedationInventory.entity.Medition;
import lombok.Data;

import java.util.List;

@Data
public class MeditionListDto {
    private List<MeditionRequest> medAndQuantityList;


    private Medition medition;
}
