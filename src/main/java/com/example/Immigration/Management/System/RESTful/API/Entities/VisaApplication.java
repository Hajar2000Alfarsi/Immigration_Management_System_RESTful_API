package com.example.Immigration.Management.System.RESTful.API.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VisaApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Applicant applicant;

    @ManyToOne
    private ImmigrationOfficer handlingOfficer;

    private String visaType;
    private String status;
    private String officerNotes;
}
