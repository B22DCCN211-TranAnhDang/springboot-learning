package vn.hocjava.spring_boot_learning.repository;

import java.util.List;

import vn.hocjava.spring_boot_learning.repository.entity.BuildingEntity;

public interface BuildingRepository {
    List<BuildingEntity> findAll(String name, Long districtId, List<String> typeCode);
}
