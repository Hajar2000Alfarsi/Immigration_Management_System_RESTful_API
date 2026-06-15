package com.example.Immigration.Management.System.RESTful.API.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImmigrationOfficer extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ImmigrationOfficerId;

    private String badgeNumber;
    private String officerRank;
    private int clearanceLevel;
    private boolean active;

    @ManyToOne
    private ImmigrationCenter  center;

    @OneToMany
    private List<Interview> interviews;
}
