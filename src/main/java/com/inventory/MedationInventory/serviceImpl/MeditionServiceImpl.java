package com.inventory.MedationInventory.serviceImpl;

import com.inventory.MedationInventory.config.Response;
import com.inventory.MedationInventory.dto.MeditionDto;
import com.inventory.MedationInventory.entity.Medition;
import com.inventory.MedationInventory.repository.MeditionRepository;
import com.inventory.MedationInventory.service.MeditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeditionServiceImpl implements MeditionService {


    @Autowired
    MeditionRepository meditionRepository;
    @Override
    public Response addMedition(MeditionDto meditionDto) {
        Optional<Medition> meditionOptional = meditionRepository.findByMeditionName(meditionDto.getMeditionName());
        if(meditionOptional.isPresent()){
            return new Response("medition exist already",meditionDto.getMeditionName(),HttpStatus.OK);
        }
        Medition medi = new Medition();
        medi.setMeditionName(meditionDto.getMeditionName());
        medi.setDiscount(meditionDto.getDiscount());
        medi.setPrise(meditionDto.getPrise());
        medi.setMfg_date(meditionDto.getMfg_date());
        medi.setExpire_date(meditionDto.getExpire_date());
        medi.setCompany_name(meditionDto.getCompany_name());
//        medi.setStock(meditionDto.getStock());
//        medi.setInStock(false);
        meditionRepository.save(medi);
        return new Response("Medition added successfully", medi, HttpStatus.OK);
    }

    @Override
    public Response getById(Integer maId) {
        Optional<Medition> meditionOptional = meditionRepository.findById(maId);
        if(!meditionOptional.isPresent()){
            return new Response("id not found", maId, HttpStatus.BAD_REQUEST);
        }
      return new Response("Medition find successfully",meditionRepository.findById(maId),HttpStatus.OK);
    }

    @Override
    public List<Medition> getAll(MeditionDto meditionDto) {
        return meditionRepository.findAll();
    }

    @Override
    public Response getByMeditionName(String meditionName){
        Optional<Medition> meditionOptional= meditionRepository.findByMeditionName(meditionName);
        if(!meditionOptional.isPresent()){
            return new Response("Medion not found", meditionName, HttpStatus.BAD_REQUEST);
        }
        return  new Response("medition find successfully", meditionRepository.findByMeditionName(meditionName),HttpStatus.OK);
    }

    @Override
    public Response delete(Integer maId) {
        Optional<Medition> meditionOptional = meditionRepository.findById(maId);
        if(!meditionOptional.isPresent()){
            return  new Response("medition not found {}", maId, HttpStatus.BAD_REQUEST);
        }
        meditionRepository.deleteById(maId);
        return new Response("medition deleted successfully",HttpStatus.OK);
    }

    @Override
    public Response updateMedition(MeditionDto meditionDto) {
        Optional<Medition> meditionOptional = meditionRepository.findById(meditionDto.getMaId());
        if(meditionOptional.isPresent()){
            Medition medition = meditionOptional.get();
            medition.setMeditionName(meditionDto.getMeditionName());
            medition.setPrise(meditionDto.getPrise());
            medition.setDiscount(meditionDto.getDiscount());
            medition.setMfg_date(meditionDto.getMfg_date());
            medition.setExpire_date(meditionDto.getExpire_date());
            medition.setCompany_name(meditionDto.getCompany_name());
            meditionRepository.save(medition);
            return new Response("medition updated successfully",medition,HttpStatus.OK);
        }
        return new Response("medition not found for meditionName {}",HttpStatus.BAD_REQUEST );
    }
}
