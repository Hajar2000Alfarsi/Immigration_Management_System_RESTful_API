package com.example.Immigration.Management.System.RESTful.API.Repositries;

import com.example.Immigration.Management.System.RESTful.API.Entities.VisaApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisaApplicationRepository extends JpaRepository<VisaApplication, Long> {
}
