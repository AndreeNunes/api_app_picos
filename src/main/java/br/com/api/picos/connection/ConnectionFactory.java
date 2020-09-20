package br.com.api.picos.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost/database_picos?useTimezone=true&serverTimezone=UTC", "root", "1234");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}