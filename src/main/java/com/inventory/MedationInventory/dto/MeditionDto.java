package com.inventory.MedationInventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class MeditionDto {
    @JsonProperty("maId")
    private Integer maId;

    @NotEmpty(message = "name can't be empty")
    private String meditionName;

    @NotNull(message = "prise catn't be null")
    private double prise;

    private String discount;

    private String expireDate;

    private String mfgDate;

    private String company_name;

    private String salt;

    private String batchNo;

    private Double quantity;

//    private int stock;

//    private boolean active;
}
