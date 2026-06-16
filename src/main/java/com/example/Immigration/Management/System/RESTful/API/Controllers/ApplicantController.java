package com.example.Immigration.Management.System.RESTful.API.Controllers;

import com.example.Immigration.Management.System.RESTful.API.DTO.ApplicantDTO;
import com.example.Immigration.Management.System.RESTful.API.Entities.Applicant;
import com.example.Immigration.Management.System.RESTful.API.Entities.AsylumSeeker;
import com.example.Immigration.Management.System.RESTful.API.Services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {
    private final ApplicantService applicantService;

    @Autowired
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping
    public ResponseEntity<ApplicantDTO> registerApplicant(@RequestBody Applicant applicant) {
        return ResponseEntity.ok(applicantService.saveApplicant(applicant));
    }

    @PostMapping("/asylum")
    public ResponseEntity<ApplicantDTO> registerAsylumSeeker(@RequestBody AsylumSeeker asylumSeeker) {
        return ResponseEntity.ok(applicantService.saveApplicant(asylumSeeker));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<ApplicantDTO>> getAllApplicants() {
        List<ApplicantDTO> applicantDTOList = applicantService.getAll();
        return ResponseEntity.ok(applicantDTOList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ApplicantDTO>> searchByNationality(@RequestParam String nationality) {
        return ResponseEntity.ok(applicantService.getByNationality(nationality));
    }

    @PutMapping("/{id}/flag")
    public ResponseEntity<ApplicantDTO> flagApplicant(@PathVariable Long id) {
        return ResponseEntity.ok(applicantService.flagCriminalRecord(id));
    }
}
