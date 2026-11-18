package vn.hocjava.spring_boot_learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.hocjava.spring_boot_learning.dto.DistricDTO;
import vn.hocjava.spring_boot_learning.service.DistricService;

@RestController
public class DistricController {

    @Autowired
    private DistricService districService;

    // Giữ lại DUY NHẤT 1 API để truy vấn danh sách Quận / Huyện
    // Hỗ trợ cả 2 đường dẫn /api/districts và /api/districs
    // Tìm kiếm động: Không truyền name -> Lấy tất cả; Truyền ?name=... -> Lọc theo tên quận
    @GetMapping({"/api/districts", "/api/districs"})
    public List<DistricDTO> getDistricts(
            @RequestParam(value = "name", required = false) String name) {
        return districService.findAll(name);
    }
}
