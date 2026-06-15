package com.example.Immigration.Management.System.RESTful.API.Repositries;

import com.example.Immigration.Management.System.RESTful.API.Entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant,Long> {

    @Query("Select a from Applicant a where a.nationality =:nationality")
    List<Applicant> findByNationality(@Param("nationality") String nationality);

    @Query("select a from Applicant a where a.applicantId=:aId")
    List<Applicant> getById(@Param("aId") String applicantId);
}
