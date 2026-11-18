package vn.hocjava.spring_boot_learning.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import vn.hocjava.spring_boot_learning.repository.BuildingRepository;
import vn.hocjava.spring_boot_learning.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

    // 1. Khai báo thông số kết nối CSDL PostgreSQL
    static final String DB_URL = "jdbc:postgresql://localhost:5432/elearning_db";
    static final String USER = "postgres";
    static final String PASS = "1234";


  
    @Override
public List<BuildingEntity> findAll(String name, Long districtId, List<String> typeCode) {
        List<BuildingEntity> list = new ArrayList<>();

        // 2. Khởi tạo SQL động với mẹo WHERE 1 = 1
        StringBuilder sql = new StringBuilder(
            "SELECT name, street, ward, numberofbasement, rentprice FROM building WHERE 1 = 1"
        );

        // 3. Validate kiểu Chuỗi: khác null và không rỗng sau trim
        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND name LIKE '%").append(name.trim()).append("%'");
        }

        // 4. Validate kiểu Số: chỉ cần khác null
        if (districtId != null) {
            sql.append(" AND district_id = ").append(districtId);
        }
        // 5. Validate List typeCode: kiểm tra khác null và không rỗng
        if (typeCode != null && !typeCode.isEmpty()) {
            // Chuyển List ["TANG_TRET", "NOI_THAT"] thành chuỗi: 'TANG_TRET', 'NOI_THAT'
            String types = typeCode.stream()
                                   .map(item -> "'" + item + "'")
                                   .collect(Collectors.joining(", "));

            // Nối câu Subquery vào SQL
            sql.append(" AND id IN (SELECT building_id FROM building_renttype br JOIN renttype rt ON br.renttype_id = rt.id WHERE rt.code IN (" + types + "))");
        }
        System.out.println("SQL đang chạy: " + sql.toString());

        // 5. Nạp Driver PostgreSQL
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi nạp Driver PostgreSQL: " + e.getMessage());
        }

        // 6. Chạy truy vấn JDBC (dùng sql.toString())
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {

            while (rs.next()) {
                BuildingEntity building = new BuildingEntity();
                building.setName(rs.getString("name"));
                building.setStreet(rs.getString("street"));
                building.setWard(rs.getString("ward"));
                building.setNumberOfBasement(rs.getInt("numberofbasement"));
                building.setRentPrice(rs.getLong("rentprice"));
                list.add(building);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi kết nối CSDL: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }



}