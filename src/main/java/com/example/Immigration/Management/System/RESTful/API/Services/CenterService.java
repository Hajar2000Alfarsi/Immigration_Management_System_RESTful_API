package com.example.Immigration.Management.System.RESTful.API.Services;

import com.example.Immigration.Management.System.RESTful.API.DTO.CenterDTO;
import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationCenter;
import com.example.Immigration.Management.System.RESTful.API.Exception.CenterException;
import com.example.Immigration.Management.System.RESTful.API.Repositries.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterService {
    CenterRepository centerRepository;

    @Autowired

    public CenterService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    public CenterDTO createCenter(CenterDTO dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw CenterException.nameMissing();
        }
        ImmigrationCenter center = new ImmigrationCenter();

        center.setName(dto.getName());
        center.setLocationCountry(dto.getLocationCountry());
        center.setType(dto.getType());
        center.setDailyCapacity(dto.getDailyCapacity());


        return CenterDTO.convertToDTO(centerRepository.save(center));
    }

    public CenterDTO getCenterById(Long id) {
        ImmigrationCenter center = centerRepository.findById(id).orElseThrow(()->
                        CenterException.IdNotFound(id));
        return CenterDTO.convertToDTO(center);
    }
}
