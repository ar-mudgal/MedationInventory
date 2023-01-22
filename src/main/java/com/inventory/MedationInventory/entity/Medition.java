package com.inventory.MedationInventory.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "medition_table")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Medition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mId;

    @Column(name="MEDITION_NAME")
    private String meditionName;

    @Column(name = "PRISE")
    private double prise;

    @Column(name = "DISCOUNT")
    private String discount;

    @Column(name="EXPIRE_DATE")
    private String expire_date;

    @Column(name = "MFG_DATE")
    private String mfg_date;

    @Column(name="COMPANY_NAME")
    private String company_name;

//    @Column(name="stock")
//    private int stock;

//    @Column(name ="in_stock")
//    private boolean inStock;


}
