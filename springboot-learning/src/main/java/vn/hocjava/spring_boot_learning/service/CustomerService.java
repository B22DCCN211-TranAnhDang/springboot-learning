package vn.hocjava.spring_boot_learning.service;

import java.util.List;

import vn.hocjava.spring_boot_learning.dto.CustomerDTO;

public interface CustomerService {
    List<CustomerDTO> findAll(String fullname,List<String> status);
}