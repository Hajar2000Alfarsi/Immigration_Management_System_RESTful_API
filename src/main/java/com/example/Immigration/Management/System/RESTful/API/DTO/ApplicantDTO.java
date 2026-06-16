package com.example.Immigration.Management.System.RESTful.API.DTO;

import com.example.Immigration.Management.System.RESTful.API.Entities.Applicant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDTO {
    private Long applicantId;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String nationality;
    private String passportNumber;

    public static ApplicantDTO convertToDTO(Applicant applicant) {
        ApplicantDTO dto = new ApplicantDTO();

        dto.setApplicantId(applicant.getApplicantId());
        dto.setFirstName(applicant.getFirstName());
        dto.setLastName(applicant.getLastName());
        dto.setGender(applicant.getGender());
        dto.setPhoneNumber(applicant.getPhoneNumber());
        dto.setEmail(applicant.getEmail());
        dto.setNationality(applicant.getNationality());
        dto.setPassportNumber(applicant.getPassportNumber());

        return dto;
    }

    public static List<ApplicantDTO> convertToDTO(List<Applicant> applicants){
        List<ApplicantDTO> dtos = new ArrayList<>();

        for (Applicant applicant : applicants) {
            dtos.add(convertToDTO(applicant));
        }
        return dtos;
    }
}
