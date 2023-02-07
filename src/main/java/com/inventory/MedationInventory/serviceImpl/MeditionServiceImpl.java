package com.inventory.MedationInventory.serviceImpl;

import com.inventory.MedationInventory.config.Response;
import com.inventory.MedationInventory.dto.MeditionDto;
import com.inventory.MedationInventory.entity.Medition;
import com.inventory.MedationInventory.repository.MeditionRepository;
import com.inventory.MedationInventory.service.MeditionService;
import com.inventory.MedationInventory.utility.DateTimeUtils;
import freemarker.template.utility.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MeditionServiceImpl implements MeditionService {


    @Autowired
    MeditionRepository meditionRepository;

    @Override
    public Response addMedition(MeditionDto meditionDto) {
        Optional<Medition> meditionOptional = meditionRepository.findByMeditionName(meditionDto.getMeditionName());
        if (meditionOptional.isPresent()) {
            return new Response("medition exist already", meditionDto.getMeditionName(), HttpStatus.OK);
        }
        Medition medi = new Medition();
        medi.setMeditionName(meditionDto.getMeditionName());
        medi.setDiscount(meditionDto.getDiscount());
        medi.setPrise(meditionDto.getPrise());
        medi.setMfgDate(DateTimeUtils.stringToLocalDate(meditionDto.getMfgDate()));
        medi.setExpireDate(DateTimeUtils.stringToLocalDate(meditionDto.getExpireDate()));
        medi.setCompany_name(meditionDto.getCompany_name());
        meditionRepository.save(medi);
        return new Response("Medition added successfully", medi, HttpStatus.OK);
    }

    @Override
    public Response getById(Integer maId) {
        Optional<Medition> meditionOptional = meditionRepository.findById(maId);
        if (!meditionOptional.isPresent()) {
            return new Response("id not found", maId, HttpStatus.BAD_REQUEST);
        }
        return new Response("Medition find successfully", meditionRepository.findById(maId), HttpStatus.OK);
    }

    @Override
    public List<Medition> getAll(MeditionDto meditionDto) {
        return meditionRepository.findAll();
    }

    @Override
    public Response getByMeditionName(String meditionName) {
        Optional<Medition> meditionOptional = meditionRepository.findByMeditionName(meditionName);
        if (!meditionOptional.isPresent()) {
            return new Response("Medion not found", meditionName, HttpStatus.BAD_REQUEST);
        }
        return new Response("medition find successfully", meditionRepository.findByMeditionName(meditionName), HttpStatus.OK);
    }

    @Override
    public Response delete(Integer maId) {
        Optional<Medition> meditionOptional = meditionRepository.findById(maId);
        if (!meditionOptional.isPresent()) {
            return new Response("medition not found {}", maId, HttpStatus.BAD_REQUEST);
        }
        meditionRepository.deleteById(maId);
        return new Response("medition deleted successfully", HttpStatus.OK);
    }

    @Override
    public Response updateMedition(MeditionDto meditionDto) {
        Optional<Medition> meditionOptional = meditionRepository.findById(meditionDto.getMaId());
        if (meditionOptional.isPresent()) {
            Medition medition = meditionOptional.get();
            medition.setMeditionName(meditionDto.getMeditionName());
            medition.setPrise(meditionDto.getPrise());
            medition.setDiscount(meditionDto.getDiscount());
            medition.setMfgDate(DateTimeUtils.stringToLocalDate(meditionDto.getMfgDate()));
            medition.setExpireDate(DateTimeUtils.stringToLocalDate(meditionDto.getExpireDate()));
            medition.setCompany_name(meditionDto.getCompany_name());
            meditionRepository.save(medition);
            return new Response("medition updated successfully", medition, HttpStatus.OK);
        }
        return new Response("medition not found for meditionName {}", HttpStatus.BAD_REQUEST);
    }

//    @Override
//    public Response getMeditionExpiredDate(LocalDate mfgDate, LocalDate expireDate) {
//        if(LocalDate.now().isAfter(expireDate)){
//            return new Response("medition is expired", expireDate,HttpStatus.OK);
//        }
//        return new Response("Medition expire date id: ", expireDate,HttpStatus.OK);
    //}
    @Override
    public Response getMeditionExpiredDate(MeditionDto meditionDto){
        Optional<Medition> meditionOptional = meditionRepository.findById(meditionDto.getMaId());
        if (!meditionOptional.isPresent()){
            return  new Response("Medition is not avaiavle", HttpStatus.BAD_REQUEST);
        }
        Medition medition =meditionOptional.get();
        medition.setMaId(meditionDto.getMaId());
        medition.setMeditionName(meditionDto.getMeditionName());
        medition.setDiscount(meditionDto.getDiscount());
        medition.setPrise(meditionDto.getPrise());
        medition.setMfgDate(DateTimeUtils.stringToLocalDate(meditionDto.getMfgDate()));
        if(LocalDate.now().isAfter(DateTimeUtils.stringToLocalDate(meditionDto.getExpireDate()))){
            return new Response("medition expired",HttpStatus.OK);
        }
        return new Response("Medition expire Date is: ",DateTimeUtils.stringToLocalDate(meditionDto.getExpireDate()),HttpStatus.OK) ;
    }
}