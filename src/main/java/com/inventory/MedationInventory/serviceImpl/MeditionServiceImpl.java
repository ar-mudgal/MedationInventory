package com.inventory.MedationInventory.serviceImpl;

import com.inventory.MedationInventory.config.Response;
import com.inventory.MedationInventory.dao.MeditionDao;
import com.inventory.MedationInventory.dto.MeditionDto;
import com.inventory.MedationInventory.dto.MeditionListDto;
import com.inventory.MedationInventory.dto.MeditionRequest;
import com.inventory.MedationInventory.entity.AddBag;
import com.inventory.MedationInventory.entity.Medition;
import com.inventory.MedationInventory.repository.AddBagRepository;
import com.inventory.MedationInventory.repository.MeditionRepository;
import com.inventory.MedationInventory.service.MeditionService;
import com.inventory.MedationInventory.utility.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Repository
public class MeditionServiceImpl implements MeditionService {


    @Autowired
    MeditionRepository meditionRepository;

    @Autowired
    MeditionDao meditionDao;

    @Autowired
    AddBagRepository addBagRepository;

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
        medi.setBatchNo(meditionDto.getBatchNo());
        medi.setSalt(meditionDto.getSalt());
        medi.setQuantity(meditionDto.getQuantity());
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
            medition.setSalt(meditionDto.getSalt());
            medition.setBatchNo(meditionDto.getBatchNo());
            medition.setQuantity(meditionDto.getQuantity());
            meditionRepository.save(medition);
            return new Response("medition updated successfully", medition, HttpStatus.OK);
        }
        return new Response("medition not found for meditionName {}", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Response getMeditionExpiredDate(MeditionDto meditionDto) {
        Optional<Medition> meditionOptional = meditionRepository.findById(meditionDto.getMaId());
        if (!meditionOptional.isPresent()) {
            return new Response("Medition is not avaiavle", HttpStatus.BAD_REQUEST);
        }
        Medition medition = meditionOptional.get();
        medition.setMaId(meditionDto.getMaId());
        medition.setMeditionName(meditionDto.getMeditionName());
        medition.setDiscount(meditionDto.getDiscount());
        medition.setPrise(meditionDto.getPrise());
        medition.setMfgDate(DateTimeUtils.stringToLocalDate(meditionDto.getMfgDate()));
        if (LocalDate.now().isAfter(DateTimeUtils.stringToLocalDate(meditionDto.getExpireDate()))) {
            return new Response("medition expired", HttpStatus.OK);
        }
        return new Response("Medition expire Date is: ", DateTimeUtils.stringToLocalDate(meditionDto.getExpireDate()), HttpStatus.OK);
    }

    @Override
    public Response getMeditionBySaltAndBatchNo(String salt, String batchNo) {
        List<MeditionDto> md = new ArrayList<>();
        if (StringUtils.hasText(salt) || StringUtils.hasText(batchNo)) {
            List<Medition> mdd = meditionDao.findBySaltOrBatchNo(salt, batchNo);
            if (mdd != null) {
                for (Medition s : mdd) {
                    MeditionDto dt = new MeditionDto();
                    dt.setMeditionName(s.getMeditionName());
                    dt.setMaId(s.getMaId());
                    dt.setDiscount(s.getDiscount());
                    dt.setBatchNo(s.getBatchNo());
                    dt.setPrise(s.getPrise());
                    dt.setCompany_name(s.getCompany_name());
                    dt.setSalt(s.getSalt());
                    dt.setExpireDate(String.valueOf(s.getExpireDate()));
                    dt.setMfgDate(String.valueOf(s.getMfgDate()));
                    md.add(dt);
                }
                return new Response("SUCCESS", md, HttpStatus.OK);
            }
        }
        return new Response("NOT FOUND", HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<AddBag> addaddMeditionToBegB(MeditionListDto meditionListto) {
        List<AddBag> addBags = new ArrayList<>();
        for (MeditionRequest medition : meditionListto.getMedAndQuantityList()) {
            AddBag addBag = new AddBag();
            addBag.setMeditionName(medition.getMeditionName());
            addBag.setQuantity(medition.getMeditionQuantity());
            addBags.add(addBag);
            addBagRepository.save(addBag);
        }
        return addBags;
    }

    //    get total buy medition price
//    Long getMeditionPrise(MeditionListto meditionListto) {
//
////        Optional<Medition> meditionOptional = meditionRepository.findByMeditionName();
////        Medition medition1 = meditionOptional.get();
//        if (medition1.getQuantity() < addBagDto.getQuantity()) {
//            System.out.println("not available");
//        }
//        double totaltab = medition1.getQuantity()- addBagDto.getQuantity();
//        double totalPrise = (addBagDto.getQuantity() * medition1.getPrise());
//        return totalPrise;
//    }


    //    @Override
//    public Response getMeditionExpiredDate(LocalDate mfgDate, LocalDate expireDate) {
//        if(LocalDate.now().isAfter(expireDate)){
//            return new Response("medition is expired", expireDate,HttpStatus.OK);
//        }
//        return new Response("Medition expire date id: ", expireDate,HttpStatus.OK);
    //}

}