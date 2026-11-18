package vn.hocjava.spring_boot_homework.customexception;

public class InvalidAgeException extends RuntimeException{

    public InvalidAgeException(String message) {
        super(message);
    }
    
}
