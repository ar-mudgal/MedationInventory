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
@RequestMapping("/medition")

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

    @GetMapping("/get/{meditionName}")
    public Response getByMeditionName(@PathVariable String meditionName){
        return meditionService.getByMeditionName(meditionName);
    }

    @DeleteMapping("/delete/{maId}")
    public Response delete(@PathVariable Integer maId){
        return meditionService.delete(maId);
    }

    @PostMapping("/update")
        public Response updateMedition(@RequestBody MeditionDto meditionDto) throws Exception{
        return meditionService.updateMedition(meditionDto);
    }

    @PostMapping("/getExpDate")
    public Response getMeditionExpiredDate(@RequestBody MeditionDto meditionDto){
        return meditionService.getMeditionExpiredDate(meditionDto);
    }

    @GetMapping()
    Response getMeditionBySaltAndBatchNo(@RequestParam(required = false) String salt,@RequestParam(required = false) String batchNo){
        return meditionService.getMeditionBySaltAndBatchNo(salt,batchNo);
    }

    @GetMapping("/addToBeg")
    public Response addMeditionToBeg(@RequestBody MeditionDto meditionDto){
        return meditionService.addMeditionToBeg(meditionDto);
    }

}
