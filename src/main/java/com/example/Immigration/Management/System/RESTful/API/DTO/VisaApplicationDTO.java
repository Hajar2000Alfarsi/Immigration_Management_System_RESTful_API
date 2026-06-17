package com.example.Immigration.Management.System.RESTful.API.DTO;

import com.example.Immigration.Management.System.RESTful.API.Entities.VisaApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisaApplicationDTO {
    private Long visaId;
    private Long applicantId;
    private Long officerId;
    private String visaType;
    private String status;
    private String officerNotes;

    public static VisaApplicationDTO convertToDTO(VisaApplication visa) {
        VisaApplicationDTO dto = new VisaApplicationDTO();

        dto.setVisaId(visa.getVisaApplicationId());

        if (visa.getApplicant() != null){
            dto.setApplicantId(visa.getApplicant().getApplicantId());
        }

        if (visa.getHandlingOfficer() != null){
            dto.setOfficerId(visa.getHandlingOfficer().getImmigrationOfficerId());
        }

        dto.setVisaType(visa.getVisaType());
        dto.setStatus(visa.getStatus());
        dto.setOfficerNotes(visa.getOfficerNotes());
        return dto;
    }

    public static List<VisaApplicationDTO> convertToDTO(List<VisaApplication> visas){

        List<VisaApplicationDTO> dtoList = new ArrayList<>();

        for (VisaApplication visa : visas){
            dtoList.add(convertToDTO(visa));
        }

        return dtoList;
    }
}
