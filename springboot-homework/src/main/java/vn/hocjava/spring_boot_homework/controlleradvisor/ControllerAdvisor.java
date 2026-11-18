package vn.hocjava.spring_boot_homework.controlleradvisor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.hocjava.spring_boot_homework.customexception.InvalidAgeException;
import vn.hocjava.spring_boot_homework.customexception.ResourceNotFoundException;
import vn.hocjava.spring_boot_homework.dto.ErrorResponseDTO;

@RestControllerAdvice 
public class ControllerAdvisor {
    @ExceptionHandler(InvalidAgeException.class)

    public ResponseEntity<ErrorResponseDTO> handlerInvalidAgeException(InvalidAgeException ex  ){
        ErrorResponseDTO error=new ErrorResponseDTO();
        error.setError(ex.getMessage());
        List<String> arr=new ArrayList<>();
        arr.add("Sinh viên chưa đủ tuổi nhập học đại học!");
        error.setDetails(arr);

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerResourceNotFoundException(ResourceNotFoundException ex){
         ErrorResponseDTO error=new ErrorResponseDTO();
         error.setError(ex.getMessage());

         List<String> arr=new ArrayList<>();
         arr.add("ID sinh viên không tồn tại trong hệ thống!");
         error.setDetails(arr);
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
