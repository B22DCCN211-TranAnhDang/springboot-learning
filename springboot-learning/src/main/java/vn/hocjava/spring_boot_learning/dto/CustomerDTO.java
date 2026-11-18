package vn.hocjava.spring_boot_learning.dto;

public class CustomerDTO {
    private String fullName;
    private String phone;   // Số điện thoại đã được che 3 số cuối (ví dụ: "0912345***")
    private String demand;

    // Getter và Setter
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDemand() { return demand; }
    public void setDemand(String demand) { this.demand = demand; }
}