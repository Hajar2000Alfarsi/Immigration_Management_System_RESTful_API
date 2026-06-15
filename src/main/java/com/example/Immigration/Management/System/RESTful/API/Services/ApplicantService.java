package com.example.Immigration.Management.System.RESTful.API.Services;

import com.example.Immigration.Management.System.RESTful.API.Entities.Applicant;
import com.example.Immigration.Management.System.RESTful.API.Entities.Interview;
import com.example.Immigration.Management.System.RESTful.API.Repositries.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantService {
    ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    Interview interview;

    @Autowired
    public ApplicantService(Interview interview) {
        this.interview = interview;
    }

    //save applicant
    public Applicant saveApplicant(Applicant applicant) throws Exception{
        if (applicant.getPassportNumber() == null || applicant.getPassportNumber().isEmpty()) {
            throw new Exception("Invalid passport number");
        }
        if (applicant.getFirstName() == null || applicant.getLastName() == null) {
            throw new Exception("First and Last name required");
        }
        if (applicant.getNationality() == null) {
            throw new Exception("Nationality required");
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


}
