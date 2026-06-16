package com.example.Immigration.Management.System.RESTful.API.Services;

import com.example.Immigration.Management.System.RESTful.API.DTO.OfficerDTO;
import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationCenter;
import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationOfficer;
import com.example.Immigration.Management.System.RESTful.API.Exception.CenterException;
import com.example.Immigration.Management.System.RESTful.API.Exception.OfficerException;
import com.example.Immigration.Management.System.RESTful.API.Repositries.CenterRepository;
import com.example.Immigration.Management.System.RESTful.API.Repositries.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficerService {
    CenterRepository centerRepository;
    OfficerRepository officerRepository;

    @Autowired
    public OfficerService(CenterRepository centerRepository, OfficerRepository officerRepository) {
        this.centerRepository = centerRepository;
        this.officerRepository = officerRepository;
    }


    public OfficerDTO promoteOfficer(Long officerId, String newRank, int newClearanceLevel){
        if (newClearanceLevel < 1 || newClearanceLevel > 5) {
            throw OfficerException.invalidClearance();
        }

        ImmigrationOfficer officer = officerRepository.findById(officerId)
                        .orElseThrow(() -> OfficerException.IdNotFound(officerId));

        officer.setOfficerRank(newRank);
        officer.setClearanceLevel(newClearanceLevel);

        return OfficerDTO.convertToDTO(officerRepository.save(officer));
    }

    public OfficerDTO transferOfficer(Long officerId, Long newCenterId){
        ImmigrationOfficer officer = officerRepository.findById(officerId).orElseThrow(()->
                OfficerException.IdNotFound(officerId));
        ImmigrationCenter center = centerRepository.findById(newCenterId).orElseThrow(()->
                CenterException.IdNotFound(newCenterId));

        officer.setCenter(center);

        return OfficerDTO.convertToDTO(officerRepository.save(officer));
    }

    //Overloading method

    public List<OfficerDTO> findOfficersByRank(String rank){
        if (rank == null) {
            throw OfficerException.rankMissing();
        }
        return OfficerDTO.convertToDTO(officerRepository.getByRank(rank));
    }

    public List<OfficerDTO> findOfficersByRank(String rank, int minimumClearanceLevel){
        if (rank == null || rank.trim().isEmpty())
        {
            throw OfficerException.rankMissing();
        }
        List<ImmigrationOfficer> all = officerRepository.getByRank(rank);
        List<ImmigrationOfficer> filtred = new ArrayList<>();

        for (ImmigrationOfficer a : all) {
            if (a.getClearanceLevel() >= minimumClearanceLevel) {
                filtred.add(a);
            }
        }

        return OfficerDTO.convertToDTO(filtred);
    }

    public OfficerDTO saveOfficer(ImmigrationOfficer officer){

        if(officer.getBadgeNumber() == null || officer.getBadgeNumber().trim().isEmpty()){

            throw OfficerException.badgeNumberMissing();
        }

        officer.setActive(true);

        return OfficerDTO.convertToDTO(officerRepository.save(officer));
    }

    public OfficerDTO getById(Long id){
        ImmigrationOfficer officer =  officerRepository.findById(id)
                        .orElseThrow(() -> OfficerException.IdNotFound(id));

        return OfficerDTO.convertToDTO(officer);
    }


}
