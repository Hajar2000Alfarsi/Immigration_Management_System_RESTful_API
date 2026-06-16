package com.example.Immigration.Management.System.RESTful.API.DTO;

import com.example.Immigration.Management.System.RESTful.API.Entities.ImmigrationCenter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterDTO {
    private Long immigrationCenterId;
    private String name;
    private String locationCountry;
    private String type;
    private int dailyCapacity;

    public static CenterDTO convertToDTO(ImmigrationCenter center) {
        CenterDTO dto = new CenterDTO();

        dto.setImmigrationCenterId(center.getImmigrationCenterId());
        dto.setName(center.getName());
        dto.setLocationCountry(center.getLocationCountry());
        dto.setType(center.getType());
        dto.setDailyCapacity(center.getDailyCapacity());

        return dto;
    }

    public static List<CenterDTO> convertToDTO(List<ImmigrationCenter> centers) {
        List<CenterDTO> dtos = new ArrayList<>();

        for (ImmigrationCenter center : centers){
            dtos.add(convertToDTO(center));
        }
        return dtos;
    }
}
