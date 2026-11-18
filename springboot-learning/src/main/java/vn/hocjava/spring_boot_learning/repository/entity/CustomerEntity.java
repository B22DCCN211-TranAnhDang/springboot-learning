package vn.hocjava.spring_boot_learning.repository.entity;

public class CustomerEntity {
    private String fullName;
    private String phone;
    private String demand; // Nhu cầu thuê (ví dụ: "Cần thuê văn phòng 100m2")

    // Getter và Setter
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDemand() { return demand; }
    public void setDemand(String demand) { this.demand = demand; }
}