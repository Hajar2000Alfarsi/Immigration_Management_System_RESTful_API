package com.example.Immigration.Management.System.RESTful.API.Services;

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

    public ImmigrationCenter createCenter(ImmigrationCenter center) {
        if (center.getImmigrationCenterId() == null){
            throw CenterException.nameMissing();
        }
        return centerRepository.save(center);
    }

    public ImmigrationCenter getCenterById(Long id) {
        return  centerRepository.findById(id).orElseThrow(()->
                CenterException.IdNotFound(id));
    }
}
