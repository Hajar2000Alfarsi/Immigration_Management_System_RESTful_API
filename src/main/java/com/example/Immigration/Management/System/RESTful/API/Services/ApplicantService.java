package com.example.Immigration.Management.System.RESTful.API.Services;

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
    public Applicant saveApplicant(Applicant applicant) throws Exception{
        if (applicant.getPassportNumber() == null || applicant.getPassportNumber().isEmpty()) {
            throw ApplicantException.invalidPassport();
        }
        if (applicant.getFirstName() == null || applicant.getLastName() == null) {
            throw ApplicantException.nameMissing();
        }
        if (applicant.getNationality() == null) {
            throw ApplicantException.nationalityMissing();
        }

        applicant.setCriminalRecord(false);
        return applicantRepository.save(applicant);
    }

    //Overloaded save applicant
    public Applicant saveApplicant(String firstName, String lastName, String passportNumber, String
            nationality) {
        Applicant applicant = new Applicant();

        applicant.setFirstName(firstName);
        applicant.setLastName(lastName);
        applicant.setPassportNumber(passportNumber);
        applicant.setNationality(nationality);
        applicant.setCriminalRecord(false);

        return applicantRepository.save(applicant);
    }

    public String flagCriminalRecord(Long applicantId) throws Exception{
        Applicant applicant = applicantRepository.getById(applicantId);

        if (applicantId == null) {
            throw ApplicantException.idMissing();
        }
        if (applicant.getApplicantId() == null){
            throw ApplicantException.IdNotFound(applicantId);
        }

        applicant.setCriminalRecord(true);
        applicantRepository.save(applicant);

        //cancel interview
        List<Interview> interviews = interviewRepository.findByOfficerId(applicantId);
        for (Interview interview : interviews) {
            if (interview.getStatus().equals("SCHEDULED")){
                interview.getStatus().equals("CANCELLED");
            }
        }

        interviewRepository.saveAll(interviews);

        return "Applicant flagged and interviews cancelled";

    }
}
