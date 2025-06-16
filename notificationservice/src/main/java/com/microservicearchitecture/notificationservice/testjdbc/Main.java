package com.microservicearchitecture.notificationservice.testjdbc;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Параметры подключения
        String url = "jdbc:mysql://localhost:3306/my_db";
        String user = "bestuser";
        String password = "bestuser";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Успешное подключение к MySQL!");

            // Пример выполнения запроса
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("select * from my_db.employees");
                System.out.println(resultSet);
                if (resultSet.next()) {
                    System.out.println("Результат тестового запроса: " + resultSet.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.err.println("Ошибка подключения: " + e.getMessage());
        }
    }
}
