package vn.hocjava.spring_boot_learning.repository.entity;

public class BuildingEntity {
    private String name;
    private String street;
    private String ward;
    private Integer numberOfBasement;
    private Long rentPrice;

    // Getter và Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getWard() {
        return ward;
    }
    public void setWard(String ward) {
        this.ward = ward;
    }
    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }
    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }
    public Long getRentPrice() {
        return rentPrice;
    }
    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }
}