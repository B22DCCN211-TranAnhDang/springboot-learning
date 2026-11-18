package vn.hocjava.spring_boot_homework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hocjava.spring_boot_homework.customexception.InvalidAgeException;
import vn.hocjava.spring_boot_homework.customexception.ResourceNotFoundException;
import vn.hocjava.spring_boot_homework.dto.StudentDTO;
import vn.hocjava.spring_boot_homework.service.StudentService;


@RestController
public class StudentController{
@Autowired
private StudentService studentService;

@GetMapping("/api/students")
public List<StudentDTO> getStudent() {
    return studentService.findAll(); // Bàn giao việc cho Service
}

    @DeleteMapping("/api/student/{id}")
    public  String delete(@PathVariable("id") Integer id){
        return "Da xoa thanh cong sinh vien co id la"+id;
    }

    @PutMapping("/api/students/{id}")
    public String put(@PathVariable("id") Integer id,
                      @RequestBody StudentDTO student){
    return "Tao sinh vien thanh cong "+student;
    }





public ResponseEntity<?> add1(@RequestBody StudentDTO student) {
    // 1. Kiểm tra tên trống
    if (student.getFullName() == null || student.getFullName().isBlank()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi: Tên sinh viên không được để trống");
    }

    // 2. KIỂM TRA TUỔI: Nếu nhỏ hơn 18 thì ném InvalidAgeException ra!
    if (student.getAge() != null && student.getAge() < 18) {
        throw new InvalidAgeException("Tuổi sinh viên phải từ 18 trở lên!");
    }

    return ResponseEntity.ok("Tạo thành công sinh viên: " + student.getFullName());
}
@GetMapping("api/students/{id}")
public ResponseEntity<?> getMethodName(@PathVariable("id") Integer id) {
    if(id >10){
        throw new ResourceNotFoundException("Khong tim thay id sinh vien");
    }
return ResponseEntity.ok("Tim thay ten sinh vien "+id);
}
    

} 