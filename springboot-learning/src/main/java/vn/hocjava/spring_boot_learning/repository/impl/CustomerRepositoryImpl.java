package vn.hocjava.spring_boot_learning.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import vn.hocjava.spring_boot_learning.repository.CustomerRepository;
import vn.hocjava.spring_boot_learning.repository.entity.CustomerEntity;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
  
    static final String DB_URL="jdbc:postgresql://localhost:5432/elearning_db";
    static final String USER="postgres";
    static final String PASSWORD="1234";

    @Override 
    public List<CustomerEntity> findAll(String fullname,List<String> status){
        List<CustomerEntity> customerEntities=new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM customer WHERE 1=1");

        // 1. Ghép điều kiện tên khách hàng (nếu có truyền vào)
        if (fullname != null && !fullname.trim().isEmpty()) {
            sql.append(" AND fullname LIKE '%").append(fullname.trim()).append("%'");
        }

        // 2. Ghép điều kiện danh sách trạng thái status (nếu có chọn ít nhất 1 cái)
        if (status != null && !status.isEmpty()) {
            String statusList = status.stream()
                .map(item -> "'" + item + "'")
                .collect(Collectors.joining(", "));
            sql.append(" AND status IN (").append(statusList).append(")");
        }

        System.out.println("SQL DANG CHAy"+sql.toString());
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try(Connection conn=DriverManager.getConnection(DB_URL,USER,PASSWORD);
              ResultSet rs=conn.createStatement().executeQuery(sql.toString()); 
    ) {
            while(rs.next()){
                CustomerEntity customerEntity=new CustomerEntity();
                customerEntity.setDemand(rs.getString("demand"));
                customerEntity.setFullName(rs.getString("fullname"));
                customerEntity.setPhone(rs.getString("phone"));
                customerEntities.add(customerEntity);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();;
        }
        return customerEntities;

    }
}
