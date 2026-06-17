package com.example.Immigration.Management.System.RESTful.API.Controllers;

import com.example.Immigration.Management.System.RESTful.API.DTO.InterviewDTO;
import com.example.Immigration.Management.System.RESTful.API.Services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {
    InterviewService interviewService;
    @Autowired

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/schedule/{applicantId}/{officerId}")
    public ResponseEntity<InterviewDTO> schedule(
            @PathVariable Long applicantId,
            @PathVariable Long officerId,
            @RequestParam String date) {
        return ResponseEntity.ok(interviewService.scheduleInterview(applicantId, officerId, date));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<InterviewDTO> complete(@PathVariable Long id) {
        return ResponseEntity.ok(interviewService.completeInterview(id));
    }

    // PUT cancel
    @PutMapping("/{id}/cancel")
    public ResponseEntity<InterviewDTO> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(interviewService.cancelInterview(id));
    }

    // GET officer schedule
    @GetMapping("/officer/{officerId}/date/{date}")
    public ResponseEntity<List<InterviewDTO>> getSchedule(
            @PathVariable Long officerId,
            @PathVariable String date
    ) {
        return ResponseEntity.ok(interviewService.getOfficerSchedule(officerId, date));
    }
}
