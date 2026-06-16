package com.example.Immigration.Management.System.RESTful.API.Services;

import com.example.Immigration.Management.System.RESTful.API.Entities.Applicant;
import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationOfficer;
import com.example.Immigration.Management.System.RESTful.API.Entities.VisaApplication;
import com.example.Immigration.Management.System.RESTful.API.Exception.ApplicantException;
import com.example.Immigration.Management.System.RESTful.API.Exception.OfficerException;
import com.example.Immigration.Management.System.RESTful.API.Exception.VisaApplicationException;
import com.example.Immigration.Management.System.RESTful.API.Repositries.ApplicantRepository;
import com.example.Immigration.Management.System.RESTful.API.Repositries.OfficerRepository;
import com.example.Immigration.Management.System.RESTful.API.Repositries.VisaApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisaApplicationService {
    VisaApplicationRepository visaApplicationRepository;
    ApplicantRepository applicantRepository;
    OfficerRepository officerRepository;

    public VisaApplicationService(VisaApplicationRepository visaApplicationRepository, ApplicantRepository applicantRepository, OfficerRepository officerRepository) {
        this.visaApplicationRepository = visaApplicationRepository;
        this.applicantRepository = applicantRepository;
        this.officerRepository = officerRepository;
    }

    public VisaApplication submitApplication(Long applicantId, String visaType){
        Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(()->
        ApplicantException.IdNotFound(applicantId));

        VisaApplication visaApplication = new VisaApplication();

        if (visaType == null || visaType.isEmpty()) {
            throw VisaApplicationException.visaTypeMissing();
        }

        visaApplication.setApplicant(applicant);
        visaApplication.setVisaType(visaType);

        //criminal record check
        if (applicant.getCriminalRecord()){
            visaApplication.setStatus("REJECTED");
            visaApplication.setOfficerNotes("Auto-rejected due to criminal flag.");
        } else {
            visaApplication.setStatus("PENDING");
        }
        return visaApplicationRepository.save(visaApplication);
    }

    public VisaApplication assignOfficer(Long visaId, Long officerId) {
        VisaApplication visaApplication = visaApplicationRepository.findById(visaId).orElseThrow(()->
                VisaApplicationException.IdNotFound(visaId));
        ImmigrationOfficer immigrationOfficer = officerRepository.findById(officerId).orElseThrow(()->
                OfficerException.IdNotFound(officerId));

        //asylum visa requires high clearance
        if ("Asylum".equalsIgnoreCase(visaApplication.getVisaType())){
            if (immigrationOfficer.getClearanceLevel() < 4){
                throw VisaApplicationException.insufficientClearance(visaApplication.getVisaType());
            }
        }
        visaApplication.setHandlingOfficer(immigrationOfficer);

        return visaApplicationRepository.save(visaApplication);
    }

    public VisaApplication processVisa(Long visaId, String newStatus, String notes) {
        VisaApplication visa = visaApplicationRepository.findById(visaId).orElseThrow(()->
                VisaApplicationException.IdNotFound(visaId));

        if (!newStatus.equalsIgnoreCase("APPROVED") && !newStatus.equalsIgnoreCase("REJECTED")) {
            throw VisaApplicationException.invalidStatus();
        }

        visa.setStatus(newStatus);
        visa.setOfficerNotes(notes);

        return visaApplicationRepository.save(visa);
    }
}
