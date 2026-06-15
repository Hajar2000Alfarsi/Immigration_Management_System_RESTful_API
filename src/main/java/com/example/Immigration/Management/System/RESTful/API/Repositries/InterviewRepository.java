package com.example.Immigration.Management.System.RESTful.API.Repositries;

import com.example.Immigration.Management.System.RESTful.API.Entities.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    @Query("select i from Interview i where i.officerId =:oId AND i.interviewDate=:date")
    List<Interview> findByOfficerIdAndInterviewDate(
            @Param("oId") Long interviewId,
            @Param("date") String interviewDate
            );
}
