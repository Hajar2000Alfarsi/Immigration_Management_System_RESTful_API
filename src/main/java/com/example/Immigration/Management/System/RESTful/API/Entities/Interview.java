package com.example.Immigration.Management.System.RESTful.API.Entities;

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
    private Applicant applicant;

    @ManyToOne
    private ImmigrationOfficer officer;

    private String interviewDate;

    private String status;

    private String purpose;
}
