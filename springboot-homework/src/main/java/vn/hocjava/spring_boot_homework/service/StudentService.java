package vn.hocjava.spring_boot_homework.service;

import java.util.List;

import vn.hocjava.spring_boot_homework.dto.StudentDTO;


public interface  StudentService {
    List<StudentDTO> findAll();
}
