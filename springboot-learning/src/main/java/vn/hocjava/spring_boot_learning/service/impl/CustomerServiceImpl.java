package vn.hocjava.spring_boot_learning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hocjava.spring_boot_learning.dto.CustomerDTO;
import vn.hocjava.spring_boot_learning.repository.CustomerRepository;
import vn.hocjava.spring_boot_learning.repository.entity.CustomerEntity;
import vn.hocjava.spring_boot_learning.service.CustomerService;

@Service 

public class CustomerServiceImpl implements CustomerService {
    @Autowired 
    private CustomerRepository customerRepository;
  public List<CustomerDTO> findAll(String fullname,List<String> status){
    List<CustomerEntity> customerDTOs=customerRepository.findAll(fullname, status);
     List<CustomerDTO> customerDTOs2=new ArrayList<>();
     for(CustomerEntity s:customerDTOs){
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setDemand(s.getDemand());
        customerDTO.setFullName(s.getFullName());
        customerDTO.setPhone(s.getPhone());
        customerDTOs2.add(customerDTO);

     }
     return customerDTOs2;
  }

}
