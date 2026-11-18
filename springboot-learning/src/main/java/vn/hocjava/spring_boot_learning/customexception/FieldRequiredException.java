package vn.hocjava.spring_boot_learning.customexception;

// Kế thừa RuntimeException để biến nó thành một Exception không bắt buộc try-catch
public class FieldRequiredException extends RuntimeException {
    public FieldRequiredException(String message) {
        super(message);
    }
}