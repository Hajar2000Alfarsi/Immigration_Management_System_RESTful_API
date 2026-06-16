package com.example.Immigration.Management.System.RESTful.API.Services;

import com.example.Immigration.Management.System.RESTful.API.DTO.ApplicantDTO;
import com.example.Immigration.Management.System.RESTful.API.Entities.Applicant;
import com.example.Immigration.Management.System.RESTful.API.Entities.Interview;
import com.example.Immigration.Management.System.RESTful.API.Exception.ApplicantException;
import com.example.Immigration.Management.System.RESTful.API.Repositries.ApplicantRepository;
import com.example.Immigration.Management.System.RESTful.API.Repositries.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantService {
    ApplicantRepository applicantRepository;
    InterviewRepository interviewRepository;

    @Autowired
    public ApplicantService(ApplicantRepository applicantRepository, InterviewRepository interviewRepository) {
        this.applicantRepository = applicantRepository;
        this.interviewRepository = interviewRepository;
    }

    //save applicant
    public ApplicantDTO saveApplicant(Applicant applicant){
        if (applicant.getPassportNumber() == null || applicant.getPassportNumber().trim().isEmpty()) {
            throw ApplicantException.invalidPassport();
        }
        if (applicant.getFirstName() == null || applicant.getFirstName().trim().isEmpty()
                || applicant.getLastName() == null || applicant.getLastName().trim().isEmpty()) {
            throw ApplicantException.nameMissing();
        }
        if (applicant.getNationality() == null || applicant.getNationality().trim().isEmpty()) {
            throw ApplicantException.nationalityMissing();
        }

        applicant.setCriminalRecord(false);
        return ApplicantDTO.convertToDTO(applicantRepository.save(applicant));
    }

    //Overloaded save applicant
    public ApplicantDTO saveApplicant(String firstName, String lastName, String passportNumber, String
            nationality) {
        Applicant applicant = new Applicant();

        applicant.setFirstName(firstName);
        applicant.setLastName(lastName);
        applicant.setPassportNumber(passportNumber);
        applicant.setNationality(nationality);
        applicant.setCriminalRecord(false);

        return ApplicantDTO.convertToDTO(applicantRepository.save(applicant));
    }

    public ApplicantDTO flagCriminalRecord(Long applicantId){
        if (applicantId == null) {
            throw ApplicantException.idMissing();
        }

        Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(()->
                ApplicantException.IdNotFound(applicantId));

        applicant.setCriminalRecord(true);
        Applicant savedApplicant = applicantRepository.save(applicant);

        //cancel interview
        List<Interview> interviews = interviewRepository.findByApplicantId(applicantId);
        for (Interview interview : interviews) {
            if ("SCHEDULED".equals(interview.getStatus())){
                interview.setStatus("CANCELLED");
            }
        }

        interviewRepository.saveAll(interviews);

        return ApplicantDTO.convertToDTO(savedApplicant);

    }

    public List<ApplicantDTO> getAll(){
        return ApplicantDTO.convertToDTO(applicantRepository.findAll());
    }

    public List<ApplicantDTO> getByNationality(String nationality) {
        return ApplicantDTO.convertToDTO(applicantRepository.findByNationality(nationality));
    }
}
