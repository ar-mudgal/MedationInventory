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
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Repository
@Slf4j
public class MeditionServiceImpl implements MeditionService {


    @Autowired
    MeditionRepository meditionRepository;

    @Autowired
    MeditionDao meditionDao;

    @Autowired
    AddBagRepository addBagRepository;

    @Override
    public Response addMedition(MeditionDto meditionDto) {
        Optional<Medition> meditionOptional = meditionRepository.getByMeditionName(meditionDto.getMeditionName());
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
        Optional<Medition> meditionOptional = meditionRepository.getByMeditionName(meditionName);
        if (!meditionOptional.isPresent()) {
            return new Response("Medion not found", meditionName, HttpStatus.BAD_REQUEST);
        }
        log.info("medition find successfully -{}",meditionName);
        return new Response("medition find successfully", meditionRepository.findByMeditionName(meditionName), HttpStatus.OK);
    }

    @Override
    public Response delete(Integer maId) {
        Optional<Medition> meditionOptional = meditionRepository.findById(maId);
        if (!meditionOptional.isPresent()) {
            log.info("Medition not found for maId- {}",maId);
            return new Response("medition not found", maId, HttpStatus.BAD_REQUEST);
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
    public Response getMeditionBySaltAndBatchNo(String salt, String batchNo) {
        List<MeditionDto> md = new ArrayList<>();
        if (StringUtils.hasText(salt) || StringUtils.hasText(batchNo)) {
            List<Medition> mdd = meditionDao.findBySaltOrBatchNo(salt, batchNo);
            if (!CollectionUtils.isEmpty(mdd)) {
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
        Optional<List<Medition>> meditions = meditionRepository.findByMeditionName(medition.getMeditionName());
        List<Medition> meditions1 = meditions.get();
            Medition medition2 = new Medition();
        for(Medition medition1 :meditions1){
            medition2.setPrise(medition1.getPrise());
            medition2.setBatchNo(medition1.getBatchNo());
            medition2.setCompany_name(medition1.getCompany_name());
            medition2.setCompany_name(medition1.getCompany_name());
            medition2.setPrise(medition1.getPrise());
            medition2.setExpireDate(medition1.getExpireDate());
            medition2.setDiscount(medition1.getDiscount());
            medition2.setSalt(medition1.getSalt());
            medition2.setQuantity(medition1.getQuantity());
            var meditionOptional = meditionRepository.findByMeditionName(medition.getMeditionName());
            if(meditionOptional.isPresent()){
                List<Medition> meditions2 = meditionOptional.get();
                Medition medition3 = new Medition();
                for(Medition medition4 : meditions2){
                    double qty = medition4.getQuantity()-medition.getMeditionQuantity();
                    medition4.setQuantity(qty);
                    meditionRepository.save(medition4);
                }
            }
        }
            AddBag addBag = new AddBag();
            addBag.setMeditionName(medition.getMeditionName());
            addBag.setQuantity(medition.getMeditionQuantity());
            addBag.setSalt(medition2.getSalt());
            addBag.setPrise(medition2.getPrise());
            addBag.setDiscount(medition2.getDiscount());
            addBag.setExpireDate(medition2.getExpireDate());
            addBag.setBuyDate(LocalDate.now());
            addBag.setBatchNo(medition2.getBatchNo());
            addBag.setCompany_name(medition2.getCompany_name());
            addBag.setMfgDate(medition2.getMfgDate());
            addBag.setExpireDate(medition2.getExpireDate());
            Double totalPrize = medition.getMeditionQuantity() * medition2.getPrise();
            addBag.setTotalPrice(totalPrize);
            addBags.add(addBag);
            addBagRepository.save(addBag);
        }
        return addBags;

    }

    public void removeMeditionFromBag(String meditionName){
        addBagRepository.deleteAll();
    }

//    public Response getMeditionExpiredDate(LocalDate mfgDate, LocalDate expireDate) {
//        if(LocalDate.now().isAfter(expireDate)){
//            return new Response("medition is expired", expireDate,HttpStatus.OK);
//        }
//        return new Response("Medition expire date id: ", expireDate,HttpStatus.OK);
//    }

    @Override
    public Response getExpiryMedition(String fromDate, String toDate){
        List<MeditionDto> meditionDtos = new ArrayList<>();
         LocalDate date1 = DateTimeUtils.stringToLocalDate(fromDate);
        LocalDate datye2 = DateTimeUtils.stringToLocalDate(toDate);
        List<Medition> meditionOptional = meditionRepository.findByfromDateToExpDate(date1,datye2);
        if(meditionOptional.isEmpty() || meditionOptional==null){
            return  new Response("No Medition is founded in between these days to be expired !",HttpStatus.BAD_REQUEST);
        }
            for(Medition m : meditionOptional){
                MeditionDto medition = new MeditionDto();
                medition.setSalt(m.getSalt());
                medition.setMeditionName(m.getMeditionName());
                medition.setQuantity(m.getQuantity());
                medition.setBatchNo(m.getBatchNo());
                medition.setPrise(m.getPrise());
                medition.setExpireDate(DateTimeUtils.loaclDateToString(m.getExpireDate()));
                medition.setCompany_name(m.getCompany_name());
                medition.setMfgDate(DateTimeUtils.loaclDateToString(m.getMfgDate()));
                medition.setMaId(m.getMaId());
                meditionDtos.add(medition);
            }
            return new Response("SUCCESS",meditionDtos, HttpStatus.OK);
        }

}




//if (LocalDate.now().isAfter(DateTimeUtils.stringToLocalDate(meditionDto.getExpireDate()))) {
//        return new Response("medition expired", HttpStatus.OK);
//        }