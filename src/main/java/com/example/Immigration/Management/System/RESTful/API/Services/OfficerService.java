package com.example.Immigration.Management.System.RESTful.API.Services;

import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationCenter;
import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationOfficer;
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


    public ImmigrationOfficer promoteOfficer(Long officerId, String newRank, int newClearanceLevel){
        if (newClearanceLevel < 1 || newClearanceLevel > 5) {
            throw OfficerException.invalidClearance();
        }

        ImmigrationOfficer officer = officerRepository.getById(officerId);

        if (officer.getImmigrationOfficerId() == null) {
            throw OfficerException.IdNotFound(officerId);
        }

        officer.setOfficerRank(newRank);
        officer.setClearanceLevel(newClearanceLevel);

        return officerRepository.save(officer);
    }

    public ImmigrationOfficer transferOfficer(Long officerId, Long newCenterId){
        ImmigrationOfficer officer = officerRepository.findById(officerId).orElseThrow(()->
                OfficerException.IdNotFound(officerId));
        ImmigrationCenter center = centerRepository.findById(newCenterId).orElseThrow(()->
                new RuntimeException("Center not found"));

        officer.setCenter(center);

        return officerRepository.save(officer);
    }

    //Overloading method

    public List<ImmigrationOfficer> findOfficersByRank(String rank){
        if (rank == null) {
            throw OfficerException.rankMissing();
        }
        return officerRepository.getByRank(rank);
    }

    public List<ImmigrationOfficer> findOfficersByRank(String rank, int minimumClearanceLevel)throws Exception{
        if (rank == null)
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

        return all;
    }
}
