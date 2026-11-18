package vn.hocjava.spring_boot_homework.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hocjava.spring_boot_homework.dto.StudentDTO;
import vn.hocjava.spring_boot_homework.repository.StudentRepository;
import vn.hocjava.spring_boot_homework.repository.entity.StudentEntity;
import vn.hocjava.spring_boot_homework.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDTO> findAll() {
        List<StudentEntity> studentList = studentRepository.findAll();
        List<StudentDTO> arr = new ArrayList<>();
        for (StudentEntity s : studentList) {
            StudentDTO a = new StudentDTO();
            a.setAge(s.getAge());
            a.setFullName(s.getFullName());
            a.setMajor(s.getMajor() + " - " + s.getHometown());
            arr.add(a);

        }
        return arr;

    }
}
