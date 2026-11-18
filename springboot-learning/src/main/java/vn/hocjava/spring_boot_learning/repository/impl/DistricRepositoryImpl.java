package vn.hocjava.spring_boot_learning.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import vn.hocjava.spring_boot_learning.repository.DistricRepository;
import vn.hocjava.spring_boot_learning.repository.entity.DistricEntity;

@Repository
public class DistricRepositoryImpl implements DistricRepository {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/elearning_db";
    static final String USER = "postgres";
    static final String PASSWORD = "1234";

    @Override
    public List<DistricEntity> findAll(String name) {
        List<DistricEntity> districEntities = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM district WHERE 1=1");

        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND name LIKE '%").append(name.trim()).append("%'");
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             ResultSet rs = conn.createStatement().executeQuery(sql.toString())) {

            while (rs.next()) {
                DistricEntity districEntity = new DistricEntity();
                districEntity.setId(rs.getLong("id"));
                districEntity.setCode(rs.getString("code"));
                districEntity.setName(rs.getString("name"));
                districEntities.add(districEntity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return districEntities;
    }
}
