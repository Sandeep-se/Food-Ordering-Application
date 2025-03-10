package com.project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
	private Jdbc() {};
	private static Connection connection=null;
	private static final String url = "jdbc:mysql://localhost:3306/project";
    private static final String user = "root";
    private static final String password = "";
    
	public static Connection getConnection(){
		if(connection==null) {
			try {
				connection=DriverManager.getConnection(url,user,password);
			}catch (SQLException e) {
	            System.out.println("Connection Failed!");
	            e.printStackTrace();
	        }
		}
		
		return connection;
	}
	public static void closeConnection() {
		if(connection!=null) {
			try {
				connection.close();
				System.out.println("connection closed");
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
