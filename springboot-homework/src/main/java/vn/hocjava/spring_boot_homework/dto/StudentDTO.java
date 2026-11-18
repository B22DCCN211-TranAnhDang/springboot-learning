package vn.hocjava.spring_boot_homework.dto;

public class StudentDTO {
    private  String fullName;
    private Integer age;
    private String major;

    public String getFullName() {
        return fullName;
    }

public StudentDTO() {
}
    public StudentDTO(String fullName, Integer age, String major) {
        this.fullName = fullName;
        this.age = age;
        this.major = major;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
