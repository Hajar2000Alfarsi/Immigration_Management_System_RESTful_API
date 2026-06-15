package com.example.Immigration.Management.System.RESTful.API.Repositries;

import com.example.Immigration.Management.System.RESTful.API.Entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant,Long> {
}
