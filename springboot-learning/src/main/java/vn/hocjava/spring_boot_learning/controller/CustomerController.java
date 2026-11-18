package vn.hocjava.spring_boot_learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.hocjava.spring_boot_learning.dto.CustomerDTO;
import vn.hocjava.spring_boot_learning.service.CustomerService;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/api/customers")
    public List<CustomerDTO> getCustomers(
        @RequestParam(value="name",required=false) String fullname,
        @RequestParam (value = "status",required = false) List<String> status){
            return customerService.findAll(fullname,status);
    } 
        
    
}