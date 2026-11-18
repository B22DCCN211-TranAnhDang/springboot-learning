package vn.hocjava.spring_boot_learning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hocjava.spring_boot_learning.dto.BuildingDTO;
import vn.hocjava.spring_boot_learning.repository.BuildingRepository;
import vn.hocjava.spring_boot_learning.repository.entity.BuildingEntity;
import vn.hocjava.spring_boot_learning.service.BuildingService;

@Service // 1. Báo cho Spring biết đây là Bean thuộc tầng Service (Logic nghiệp vụ)
public class BuildingServiceImpl implements BuildingService {

    @Autowired // 2. Tự động tiêm BuildingRepository vào (không cần dùng new)
    private BuildingRepository buildingRepository;
        @Override
    public List<BuildingDTO> findAll(String name, Long districtId,List<String> typeCode) {
        // 1. Gọi Tầng Repository lấy Entity thô từ DB theo điều kiện lọc
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(name, districtId,typeCode);

        // 2. Tạo danh sách DTO để chứa kết quả
        List<BuildingDTO> result = new ArrayList<>();

        // 3. Vòng lặp chuyển đổi từng Entity sang DTO
        for (BuildingEntity item : buildingEntities) {
            BuildingDTO buildingDTO = new BuildingDTO();
            buildingDTO.setName(item.getName());
            buildingDTO.setNumberOfBasement(item.getNumberOfBasement());
            buildingDTO.setRentPrice(item.getRentPrice());

            // Xử lý nghiệp vụ: Ghép đường và phường thành địa chỉ hoàn chỉnh
            buildingDTO.setAddress(item.getStreet() + ", " + item.getWard());

            result.add(buildingDTO);
        }

        return result;
    }
}