package vn.hocjava.spring_boot_learning.service;

import java.util.List;

import vn.hocjava.spring_boot_learning.dto.DistricDTO;

public interface DistricService {
    List<DistricDTO> findAll(String name);
}
