package com.example.Immigration.Management.System.RESTful.API.Repositries;

import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationCenter;
import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfficerRepository extends JpaRepository<ImmigrationOfficer, Long> {
    @Query("select c from ImmigrationOfficer c where c.ImmigrationOfficerId=:oId")
    ImmigrationOfficer getById(@Param("oId") Long ImmigrationOfficerId);

    @Query("select c from ImmigrationOfficer c where c.officerRank=:orank")
    List<ImmigrationOfficer> getByRank(@Param("orank") String officerRank);
}
