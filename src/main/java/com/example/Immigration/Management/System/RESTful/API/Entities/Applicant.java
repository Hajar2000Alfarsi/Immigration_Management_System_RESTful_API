package com.example.Immigration.Management.System.RESTful.API.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Inheritance(strategy = InheritanceType.JOINED)
public class Applicant extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantId;


    private String passportNumber;
    private String nationality;
    private Boolean criminalRecord;

    @OneToMany(mappedBy = "applicant")
    private List<VisaApplication> visaApplication;

    @OneToMany(mappedBy = "applicant")
    private List<Interview> interviews;

}
