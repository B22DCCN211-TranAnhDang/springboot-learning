package vn.hocjava.spring_boot_homework.repository.entity;

public class StudentEntity {
    private String fullName;
    private Integer age;
    private String major;
    private String hometown; // Quê quán (Dưới DB lưu chi tiết quê quán)

    public StudentEntity() {
    }

    public StudentEntity(String fullName, Integer age, String major, String hometown) {
        this.fullName = fullName;
        this.age = age;
        this.major = major;
        this.hometown = hometown;
    }

    public String getFullName() {
        return fullName;
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

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
}
