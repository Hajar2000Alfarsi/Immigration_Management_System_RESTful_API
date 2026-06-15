package com.example.Immigration.Management.System.RESTful.API.Services;

import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationOfficer;
import com.example.Immigration.Management.System.RESTful.API.Repositries.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficerService {
    CenterRepository centerRepository;

    @Autowired
    public OfficerService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    public ImmigrationOfficer promoteOfficer(Long officerId, String newRank, int newClearanceLevel) throws Exception{
        if (newClearanceLevel < 1 || newClearanceLevel > 5) {
            throw new Exception("Clearance must be 1-5");
        }

        ImmigrationOfficer officer = centerRepository.getById(officerId);

        if (officer.getImmigrationOfficerId() == null) {
            throw new Exception("This officer ID not found");
        }

        officer.setOfficerRank(newRank);
        officer.setClearanceLevel(newClearanceLevel);

        return centerRepository.save(officer);
    }
}
