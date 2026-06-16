package com.example.Immigration.Management.System.RESTful.API.Services;

import com.example.Immigration.Management.System.RESTful.API.Entities.Applicant;
import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationOfficer;
import com.example.Immigration.Management.System.RESTful.API.Entities.Interview;
import com.example.Immigration.Management.System.RESTful.API.Exception.ApplicantException;
import com.example.Immigration.Management.System.RESTful.API.Exception.InterviewException;
import com.example.Immigration.Management.System.RESTful.API.Exception.OfficerException;
import com.example.Immigration.Management.System.RESTful.API.Repositries.ApplicantRepository;
import com.example.Immigration.Management.System.RESTful.API.Repositries.InterviewRepository;
import com.example.Immigration.Management.System.RESTful.API.Repositries.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {
    InterviewRepository interviewRepository;
    ApplicantRepository applicantRepository;
    OfficerRepository officerRepository;

    @Autowired

    public InterviewService(OfficerRepository officerRepository, InterviewRepository interviewRepository, ApplicantRepository applicantRepository) {
        this.officerRepository = officerRepository;
        this.interviewRepository = interviewRepository;
        this.applicantRepository = applicantRepository;
    }

    public Interview cheduleInterview(Long applicantId, Long officerId, String date){
        Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(()->
                ApplicantException.IdNotFound(applicantId));
        ImmigrationOfficer officer = officerRepository.findById(officerId).orElseThrow(()->
                OfficerException.IdNotFound(officerId));

        List<Interview> existing = interviewRepository.findByOfficerIdAndInterviewDate(officerId,date);

        if (!existing.isEmpty()){
            throw InterviewException.doubleBooked();
        }

        Interview interview = new Interview();
        interview.setApplicant(applicant);
        interview.setOfficer(officer);
        interview.setInterviewDate(date);
        interview.setStatus("SCHEDULED");

        return interviewRepository.save(interview);
    }

    public Interview completeInterview(Long interviewId){
        Interview interview = interviewRepository.findById(interviewId).orElseThrow(()->
                InterviewException.IdNotFound(interviewId));

        interview.setStatus("COMPLETED");
        return interviewRepository.save(interview);
    }
}
