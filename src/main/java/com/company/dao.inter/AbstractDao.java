package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDao {

public Connection connect() throws Exception{
//        Class.forName("com.mysql.jdbc.Driver");
        com.mysql.jdbc.Driver s;
        String url = "jdbc:mysql://localhost:3306/resume?serverTimezone=UTC";
        String username = "root";
        String password = "1234";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
}
