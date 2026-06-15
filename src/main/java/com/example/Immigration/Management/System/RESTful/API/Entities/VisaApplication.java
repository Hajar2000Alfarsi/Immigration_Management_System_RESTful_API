package com.example.Immigration.Management.System.RESTful.API.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long visaApplicationId;

    @ManyToOne
    @JsonIgnore
    private Applicant applicant;

    @ManyToOne
    @JsonIgnore
    private ImmigrationOfficer handlingOfficer;

    private String visaType;
    private String status;
    private String officerNotes;
}
