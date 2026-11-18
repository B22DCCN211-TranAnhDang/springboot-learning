package vn.hocjava.spring_boot_homework.repository;

import java.util.List;

import vn.hocjava.spring_boot_homework.repository.entity.StudentEntity;

public interface StudentRepository {
    List<StudentEntity> findAll();
}
