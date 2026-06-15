package com.example.Immigration.Management.System.RESTful.API.Repositries;

import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepository extends JpaRepository<ImmigrationCenter, Long> {
}
