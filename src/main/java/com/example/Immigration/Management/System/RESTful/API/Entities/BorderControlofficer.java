package com.example.Immigration.Management.System.RESTful.API.Entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorderControlofficer extends ImmigrationOfficer{
    private String assignedCheckpoint;
    private boolean k9UnitAssigned;
}
