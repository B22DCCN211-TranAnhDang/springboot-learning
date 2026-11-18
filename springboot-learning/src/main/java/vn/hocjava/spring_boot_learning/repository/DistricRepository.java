package vn.hocjava.spring_boot_learning.repository;

import java.util.List;

import vn.hocjava.spring_boot_learning.repository.entity.DistricEntity;

public interface DistricRepository {
    List<DistricEntity> findAll(String name);
}
