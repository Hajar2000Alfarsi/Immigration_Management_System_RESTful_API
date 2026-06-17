package com.example.Immigration.Management.System.RESTful.API.Controllers;

import com.example.Immigration.Management.System.RESTful.API.DTO.VisaApplicationDTO;
import com.example.Immigration.Management.System.RESTful.API.Entities.VisaApplication;
import com.example.Immigration.Management.System.RESTful.API.Services.VisaApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visas")
public class VisaApplicationController {
    VisaApplicationService visaApplicationService;

    @Autowired

    public VisaApplicationController(VisaApplicationService visaApplicationService) {
        this.visaApplicationService = visaApplicationService;
    }

    @PostMapping("/submit/{applicantId}")
    public ResponseEntity<VisaApplicationDTO> submitVisa(
            @PathVariable Long applicantId,
            @RequestParam String type){

        return ResponseEntity.ok(visaApplicationService.submitApplication(applicantId, type));
    }

    @PutMapping("/{visaId}/assign/{officerId}")
    public ResponseEntity<VisaApplicationDTO> assignOfficer(
            @PathVariable Long visaId,
            @PathVariable Long officerId){

        return ResponseEntity.ok(visaApplicationService.assignOfficer(visaId, officerId));
    }

    @PutMapping("/{visaId}/process")
    public ResponseEntity<VisaApplicationDTO> processVisa(
            @PathVariable Long visaId,
            @RequestParam String status,
            @RequestParam String notes){

        return ResponseEntity.ok(visaApplicationService.processVisa(visaId, status, notes));
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<VisaApplicationDTO>> getApplicantVisas(@PathVariable Long applicantId){

        return ResponseEntity.ok(visaApplicationService.getApplicantVisas(applicantId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<VisaApplicationDTO>> getByStatus(@PathVariable String status){

        return ResponseEntity.ok(visaApplicationService.getByStatus(status));
    }


}

