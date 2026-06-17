package com.example.Immigration.Management.System.RESTful.API.DTO;

import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationOfficer;
import com.example.Immigration.Management.System.RESTful.API.Entities.Interview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewDTO {
    private Long interviewId;
    private Long applicantId;
    private Long officerId;
    private String interviewDate;
    private String status;
    private String purpose;

    public static InterviewDTO convertToDTO(Interview interview) {
        InterviewDTO dto = new InterviewDTO();

        dto.setInterviewId(interview.getInterviewId());
        dto.setInterviewDate(interview.getInterviewDate());
        dto.setStatus(interview.getStatus());
        dto.setPurpose(interview.getPurpose());

        if (interview.getApplicant() != null)
            dto.setApplicantId(interview.getApplicant().getApplicantId());

        if (interview.getOfficer() != null)
            dto.setOfficerId(interview.getOfficer().getImmigrationOfficerId());

        return dto;
    }

    public static List<InterviewDTO> convertToDTO(List<Interview> interviews) {
        List<InterviewDTO> dtos = new ArrayList<>();

        for(Interview interview : interviews){
            dtos.add(convertToDTO(interview));
        }

        return dtos;
    }
}
