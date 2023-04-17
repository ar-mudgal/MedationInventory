package com.inventory.MedationInventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MeditionRequest {

    private String meditionName;

    private Long meditionQuantity;
}
