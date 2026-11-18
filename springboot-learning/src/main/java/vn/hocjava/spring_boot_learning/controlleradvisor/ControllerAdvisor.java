package vn.hocjava.spring_boot_learning.controlleradvisor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.hocjava.spring_boot_learning.customexception.FieldRequiredException;
import vn.hocjava.spring_boot_learning.dto.ErrorResponseDTO;

@RestControllerAdvice
public class ControllerAdvisor {

    // Bắt đúng loại ngoại lệ FieldRequiredException
    @ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<ErrorResponseDTO> handleFieldRequiredException(FieldRequiredException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setError(ex.getMessage());

        List<String> details = new ArrayList<>();
        details.add("Vui lòng kiểm tra lại các trường bắt buộc không được để trống!");
        errorResponse.setDetails(details);

        // Trả về đúng mã 400 và body JSON lỗi chuyên nghiệp
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorResponseDTO> handleArithmeticExeption(ArithmeticException ex){
        ErrorResponseDTO error=new ErrorResponseDTO();
        error.setError("loi chia cho 0");
        List<String> arr=new ArrayList<>();
         arr.add("Hệ thống phát hiện phép chia cho 0.");
         error.setDetails(arr);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);

    }

}