package com.example.Immigration.Management.System.RESTful.API.Controllers;

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
    public ImmigrationCenter createCenter(@RequestBody ImmigrationCenter center) {
        return centerService.createCenter(center);
    }

    @GetMapping("/{id}")
    public ImmigrationCenter getCenter(@RequestParam Long centerId) {
        return centerService.getCenterById(centerId);
    }

    @GetMapping("getAll")
    public List<ImmigrationCenter> getAllCenters(){
        return centerRepository.findAll();
    }
}
