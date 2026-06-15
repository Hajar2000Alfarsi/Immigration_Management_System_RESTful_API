package com.example.Immigration.Management.System.RESTful.API.Repositries;

import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationCenter;
import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CenterRepository extends JpaRepository<ImmigrationCenter, Long> {
    @Query("select c from ImmigrationCenter c where c.ImmigrationCenterId=:oId")
    ImmigrationCenter getById(@Param("oId") Long ImmigrationCenterId);
}
