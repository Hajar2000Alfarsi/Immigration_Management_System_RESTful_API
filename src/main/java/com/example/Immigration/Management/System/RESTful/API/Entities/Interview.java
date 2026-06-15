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
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interviewId;

    @ManyToOne
    @JsonIgnore
    private Applicant applicant;

    @ManyToOne
    @JsonIgnore
    private ImmigrationOfficer officer;

    private String interviewDate;

    private String status;

    private String purpose;
}
