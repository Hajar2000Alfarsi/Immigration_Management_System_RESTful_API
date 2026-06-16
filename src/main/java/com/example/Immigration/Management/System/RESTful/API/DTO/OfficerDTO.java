package com.example.Immigration.Management.System.RESTful.API.DTO;

import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationOfficer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficerDTO {
    private Long officerId;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String badgeNumber;
    private String officerRank;
    private int clearanceLevel;
    private boolean active;

    private Long centerId;

    public static OfficerDTO convertToDTO(ImmigrationOfficer officer){

        OfficerDTO dto = new OfficerDTO();

        dto.setOfficerId(officer.getImmigrationOfficerId());

        dto.setFirstName(officer.getFirstName());
        dto.setLastName(officer.getLastName());
        dto.setGender(officer.getGender());
        dto.setPhoneNumber(officer.getPhoneNumber());
        dto.setEmail(officer.getEmail());

        dto.setBadgeNumber(officer.getBadgeNumber());
        dto.setOfficerRank(officer.getOfficerRank());
        dto.setClearanceLevel(officer.getClearanceLevel());
        dto.setActive(officer.getActive());

        if(officer.getCenter() != null){
            dto.setCenterId(
                    officer.getCenter().getImmigrationCenterId()
            );
        }
        return dto;
    }

    public static List<OfficerDTO> convertToDTO(
            List<ImmigrationOfficer> officers){

        List<OfficerDTO> dtos = new ArrayList<>();

        for(ImmigrationOfficer officer : officers){
            dtos.add(convertToDTO(officer));
        }

        return dtos;
    }

}
