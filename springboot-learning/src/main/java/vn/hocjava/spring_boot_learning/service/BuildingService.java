package vn.hocjava.spring_boot_learning.service;

import java.util.List;

import vn.hocjava.spring_boot_learning.dto.BuildingDTO;

public interface BuildingService {
    // Tầng Service cam kết trả về danh sách DTO cho Controller
   
     List<BuildingDTO> findAll(String name, Long districtId,List<String> typeCode);

}