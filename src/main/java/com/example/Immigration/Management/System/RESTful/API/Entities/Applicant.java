package com.example.Immigration.Management.System.RESTful.API.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Applicant extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantId;


    private String passportNumber;
    private String nationality;
    private boolean criminalRecord;

    @OneToMany(mappedBy = "applicant")
    private List<VisaApplication> visaApplication;

    @OneToMany(mappedBy = "applicant")
    private List<Interviews> interviews;

}
