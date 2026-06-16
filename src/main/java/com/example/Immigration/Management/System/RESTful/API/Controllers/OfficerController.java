package com.example.Immigration.Management.System.RESTful.API.Controllers;

import com.example.Immigration.Management.System.RESTful.API.DTO.OfficerDTO;
import com.example.Immigration.Management.System.RESTful.API.Entities.BorderControlOfficer;
import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationOfficer;
import com.example.Immigration.Management.System.RESTful.API.Services.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/officers")
public class OfficerController {

    @Autowired
    OfficerService officerService;

    @PostMapping
    public ResponseEntity<OfficerDTO> hireOfficer(@RequestBody ImmigrationOfficer officer){
        return ResponseEntity.ok(officerService.saveOfficer(officer));
    }

    @PostMapping("/border")
    public ResponseEntity<OfficerDTO> hireBorderOfficer(@RequestBody BorderControlOfficer officer){

        return ResponseEntity.ok(officerService.saveOfficer(officer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfficerDTO> getOfficer(@PathVariable Long id){

        return ResponseEntity.ok(officerService.getById(id));
    }

    @PutMapping("/{id}/promote")
    public ResponseEntity<OfficerDTO> promoteOfficer(
            @PathVariable Long id,
            @RequestParam String rank,
            @RequestParam int clearance){

        return ResponseEntity.ok(officerService.promoteOfficer(id,rank,clearance));
    }

    @PutMapping("/{id}/transfer/{centerId}")
    public ResponseEntity<OfficerDTO> transferOfficer(
            @PathVariable Long id,
            @PathVariable Long centerId){

        return ResponseEntity.ok(officerService.transferOfficer(id,centerId));
    }

}
