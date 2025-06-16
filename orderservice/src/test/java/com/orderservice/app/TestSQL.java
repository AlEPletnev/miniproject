package com.orderservice.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestSQL {

//    @Test
//    void testConnection() throws SQLException {
//        String url = "jdbc:postgresql://localhost:5432/order_service_db";
//        try (Connection conn = DriverManager.getConnection(url, "postgres", "1234")) {
//            assertFalse(conn.isClosed());
//        }
//    }
}
