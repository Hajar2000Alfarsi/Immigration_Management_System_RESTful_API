package com.example.Immigration.Management.System.RESTful.API.Controllers;

import com.example.Immigration.Management.System.RESTful.API.Entities.*;
import com.example.Immigration.Management.System.RESTful.API.Repositries.ApplicantRepository;
import com.example.Immigration.Management.System.RESTful.API.Repositries.CenterRepository;
import com.example.Immigration.Management.System.RESTful.API.Repositries.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DatabaseSeedController {
    CenterRepository centerRepository;
    OfficerRepository officerRepository;
    ApplicantRepository applicantRepository;

    @Autowired

    public DatabaseSeedController(CenterRepository centerRepository, ApplicantRepository applicantRepository, OfficerRepository officerRepository) {
        this.centerRepository = centerRepository;
        this.applicantRepository = applicantRepository;
        this.officerRepository = officerRepository;
    }

    @PutMapping("/seed")
    public String seedDatabase(){
        //center
        ImmigrationCenter center1 = new ImmigrationCenter();
        center1.setName("Dubai Immigration Center");
        center1.setLocationCountry("UAE");
        center1.setType("International");
        center1.setDailyCapacity(600);
        centerRepository.save(center1);

        ImmigrationCenter center2 = new ImmigrationCenter();
        center2.setName("kuwait Immigration Center");
        center2.setLocationCountry("kuwait");
        center2.setType("Regional");
        center2.setDailyCapacity(350);

        centerRepository.save(center2);
        //Officers
        //officer1
        ImmigrationOfficer officer1 = new ImmigrationOfficer();

        officer1.setFirstName("Ahmed");
        officer1.setLastName("Ali");
        officer1.setGender("Male");
        officer1.setPhoneNumber("91234567");
        officer1.setEmail("ahmed@immigration.om");

        officer1.setBadgeNumber("OF001");
        officer1.setOfficerRank("Junior");
        officer1.setClearanceLevel(2);
        officer1.setActive(true);

        officer1.setCenter(center1);

        officerRepository.save(officer1);

        //officer2
        ImmigrationOfficer officer2 = new ImmigrationOfficer();

        officer2.setFirstName("Mohammed");
        officer2.setLastName("Said");
        officer2.setGender("Male");
        officer2.setPhoneNumber("92345678");
        officer2.setEmail("mohammed@immigration.om");

        officer2.setBadgeNumber("OF002");
        officer2.setOfficerRank("Senior");
        officer2.setClearanceLevel(4);
        officer2.setActive(true);

        officer2.setCenter(center2);

        officerRepository.save(officer2);

        //officer3
        BorderControlOfficer officer3 = new BorderControlOfficer();
        officer3.setFirstName("Khalid");
        officer3.setLastName("Hassan");
        officer3.setGender("Male");
        officer3.setPhoneNumber("93456789");
        officer3.setEmail("khalid@test.com");

        officer3.setBadgeNumber("BC001");
        officer3.setOfficerRank("Border Control");
        officer3.setClearanceLevel(5);
        officer3.setActive(true);

        officer3.setCenter(center1);

        officer3.setAssignedCheckpoint("Muscat Airport");
        officer3.setK9UnitAssigned(true);

        officerRepository.save(officer3);

        //Applicants
        //Applicant1
        Applicant applicant1 = new Applicant();
        applicant1.setFirstName("Omar");
        applicant1.setLastName("Ali");
        applicant1.setPassportNumber("P10001");
        applicant1.setNationality("Yemeni");
        applicant1.setEmail("omar@test.com");
        applicant1.setPassportNumber("98334545");
        applicant1.setCriminalRecord(false);

        //Applicant2
        Applicant applicant2 = new Applicant();
        applicant2.setFirstName("John");
        applicant2.setLastName("Smith");
        applicant2.setPassportNumber("P10002");
        applicant2.setNationality("American");
        applicant1.setEmail("john@test.com");
        applicant1.setPassportNumber("98876545");
        applicant2.setCriminalRecord(true);

        //Applicant3
        Applicant applicant3 = new Applicant();
        applicant3.setFirstName("Fatima");
        applicant3.setLastName("Hassan");
        applicant3.setPassportNumber("P10003");
        applicant3.setNationality("Somali");
        applicant1.setEmail("fatima@test.com");
        applicant1.setPassportNumber("98879845");
        applicant3.setCriminalRecord(false);

        //Applicant4
        AsylumSeeker applicant4 = new AsylumSeeker();
        applicant4.setFirstName("Ali");
        applicant4.setLastName("Mohammed");
        applicant4.setPassportNumber("P10004");
        applicant4.setNationality("Syrian");
        applicant4.setCriminalRecord(false);
        applicant4.setCountryOfOrigin("Syria");
        applicant1.setEmail("ali@test.com");
        applicant1.setPassportNumber("99873545");
        applicant4.setSponsororganization("UNHCR");

        applicantRepository.save(applicant1);
        applicantRepository.save(applicant2);
        applicantRepository.save(applicant3);
        applicantRepository.save(applicant4);

        return "Database seeded successfully!";
    }
}
