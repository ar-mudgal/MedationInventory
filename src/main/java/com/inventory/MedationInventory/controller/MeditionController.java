package com.inventory.MedationInventory.controller;

import com.inventory.MedationInventory.config.Response;
import com.inventory.MedationInventory.dto.MeditionDto;
import com.inventory.MedationInventory.entity.Medition;
import com.inventory.MedationInventory.service.MeditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MeditionController {

    @Autowired
    MeditionService meditionService;

    @PostMapping("/addMedition")
    public Response addMedition(@Valid @RequestBody MeditionDto meditionDto){
        return meditionService.addMedition(meditionDto);
    }

    @GetMapping("/getById/{mId}")
    public Response getById(@PathVariable Integer mId){
        return meditionService.getById(mId);
    }

    @GetMapping("/fetchAll")
    public  List<Medition> getAll(MeditionDto meditionDto){
        return meditionService.getAll(meditionDto);
    }

    @GetMapping("/Get/{meditionName}")
    public Response getByMeditionName(@PathVariable String meditionName){
        return meditionService.getByMeditionName(meditionName);
    }

}
