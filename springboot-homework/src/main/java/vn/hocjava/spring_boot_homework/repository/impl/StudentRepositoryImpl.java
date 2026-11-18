package vn.hocjava.spring_boot_homework.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import vn.hocjava.spring_boot_homework.repository.StudentRepository;
import vn.hocjava.spring_boot_homework.repository.entity.StudentEntity;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    @Override
    public List<StudentEntity> findAll() {
        List<StudentEntity> studentEntities = new ArrayList<>();
        StudentEntity a = new StudentEntity();
        a.setAge(22);
        a.setFullName("Tran Anh Dang");
        a.setHometown("Ha Nam");
        a.setMajor("CNTT");
        studentEntities.add(a);
        StudentEntity b = new StudentEntity();
        b.setAge(25);
        b.setFullName("Nguyen thanh dat");
        b.setHometown("Quang Ninh");
        b.setMajor("Thiet ke do hoa");
        studentEntities.add(b);

        return studentEntities;
    }

}
