package com.example.Immigration.Management.System.RESTful.API.Repositries;

import com.example.Immigration.Management.System.RESTful.API.Entities.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
}
