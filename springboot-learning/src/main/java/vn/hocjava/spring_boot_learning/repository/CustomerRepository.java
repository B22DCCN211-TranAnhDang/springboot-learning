package vn.hocjava.spring_boot_learning.repository;

import java.util.List;

import vn.hocjava.spring_boot_learning.repository.entity.CustomerEntity;

public interface CustomerRepository {
List<CustomerEntity> findAll(String fullname,List<String> status);
}