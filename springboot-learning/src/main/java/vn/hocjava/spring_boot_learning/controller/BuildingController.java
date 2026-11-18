package vn.hocjava.spring_boot_learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.hocjava.spring_boot_learning.customexception.FieldRequiredException;
import vn.hocjava.spring_boot_learning.dto.BuildingDTO;
import vn.hocjava.spring_boot_learning.service.BuildingService;

@RestController
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    
        @GetMapping("/api/buildings")
    public List<BuildingDTO> getBuildings(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "districtId", required = false) Long districtId,
            @RequestParam (value = "typeCode",required =false ) List<String> typeCode ){
        return buildingService.findAll(name, districtId,typeCode);
    }

    
    @PostMapping("/api/buildings")
    public String createBuilding(@RequestBody BuildingDTO buildingDTO) {
        return "Tạo thành công tòa nhà: " + buildingDTO.getName() 
                + " với số tầng hầm: " + buildingDTO.getNumberOfBasement() + " - Giá thuê: " + buildingDTO.getRentPrice();
    }

    @DeleteMapping("/api/buildings/{id}")
    public String deleteBuilding(@PathVariable("id") Long id) {
        return "Đã xóa thành công tòa nhà có id: " + id;
    }

    @PutMapping("/api/buildings/{id}")
    public String updateBuilding(@PathVariable("id") Long id, 
                                 @RequestBody BuildingDTO buildingDTO) {
        return "Cập nhật tòa nhà có id: " + id 
                + " thành tên mới: " + buildingDTO.getName();
    }

@PostMapping("/api/buildings/test-exception")
public ResponseEntity<?> testException(@RequestBody BuildingDTO buildingDTO) {
    if (buildingDTO.getName() == null || buildingDTO.getName().isBlank()) {
        // Chỉ việc ném lỗi ra, không cần quan tâm ResponseEntity ở đây nữa!
        throw new FieldRequiredException("Tên tòa nhà đang bị bỏ trống!");
    }
    return ResponseEntity.ok("Tòa nhà hợp lệ!");
}
@GetMapping("/api/buildings/test-arithmetic")
public String testArithmetic() {
    // Cố tình chia cho 0 để Java quăng ra ArithmeticException
    int result = 10 / 0; 
    return "Kết quả: " + result;
}
}