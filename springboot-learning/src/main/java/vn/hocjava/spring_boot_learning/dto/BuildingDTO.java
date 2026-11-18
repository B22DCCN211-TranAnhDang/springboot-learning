package vn.hocjava.spring_boot_learning.dto;

public class BuildingDTO {
    private String name;
    private String address; // 👉 Mới thêm trường này
    private Integer numberOfBasement;
    private Long rentPrice;

    // Getter và Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; } // 👉 Mới thêm
    public void setAddress(String address) { this.address = address; } // 👉 Mới thêm

    public Integer getNumberOfBasement() { return numberOfBasement; }
    public void setNumberOfBasement(Integer numberOfBasement) { this.numberOfBasement = numberOfBasement; }

    public Long getRentPrice() { return rentPrice; }
    public void setRentPrice(Long rentPrice) { this.rentPrice = rentPrice; }
}