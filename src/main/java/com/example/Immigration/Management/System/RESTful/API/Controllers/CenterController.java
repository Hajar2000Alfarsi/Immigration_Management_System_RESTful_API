package com.example.Immigration.Management.System.RESTful.API.Controllers;

import com.example.Immigration.Management.System.RESTful.API.DTO.CenterDTO;
import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationCenter;
import com.example.Immigration.Management.System.RESTful.API.Repositries.CenterRepository;
import com.example.Immigration.Management.System.RESTful.API.Services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
public class CenterController {
    @Autowired
    CenterService centerService;

    @Autowired
    CenterRepository centerRepository;

    @PutMapping
    public ResponseEntity<CenterDTO> createCenter(@RequestBody CenterDTO dto) {
        return ResponseEntity.ok(centerService.createCenter(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CenterDTO> getCenter(@PathVariable Long id) {
        return ResponseEntity.ok(centerService.getCenterById(id));

    }

    @GetMapping("getAll")
    public ResponseEntity<List<CenterDTO>> getAllCenters(){
        return ResponseEntity.ok(CenterDTO.convertToDTO(centerRepository.findAll()));
    }
}
