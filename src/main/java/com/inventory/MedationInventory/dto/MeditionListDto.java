package com.inventory.MedationInventory.dto;

import com.inventory.MedationInventory.entity.Medition;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MeditionListDto {
    private List<MeditionRequest> medAndQuantityList;

    private List<Map<String,Long>> medicineQuantObj;
}
