package com.example.Immigration.Management.System.RESTful.API.Entities;

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
    private long ImmigrationOfficerId;

    private String badgeNumber;
    private String rank;
    private int clearanceLevel;
    private boolean active;

    @ManyToOne
    private ImmigrationOfficer  center;

    @OneToMany(mappedBy = "officer")
    private List<Interview> interviews;
}
