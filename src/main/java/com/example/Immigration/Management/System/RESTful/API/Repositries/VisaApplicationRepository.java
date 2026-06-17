package com.example.Immigration.Management.System.RESTful.API.Repositries;

import com.example.Immigration.Management.System.RESTful.API.Entities.VisaApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisaApplicationRepository extends JpaRepository<VisaApplication, Long> {
    @Query("select v from VisaApplication v where v.applicant.applicantId=:applicantId")
    List<VisaApplication> findByApplicantId(@Param("applicantId") Long applicantId);

    @Query("select v from VisaApplication v where v.status=:status")
    List<VisaApplication> findByStatus(@Param("status") String status);
}
