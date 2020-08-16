package com.company.dao.impl;

import com.company.bean.User;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    public List<User> getAll() throws Exception{
        List<User> result = new ArrayList();

        Connection c = connect();
        Statement stmt = c.createStatement();
        stmt.execute("SELECT * FROM user");
        ResultSet rs = stmt.getResultSet();

        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String phone = rs.getString("phone");

            System.out.println("id="+id);
            System.out.println("name="+name);
            System.out.println("surname="+surname);
            System.out.println("email="+email);
            System.out.println("phone="+phone);

            result.add(new User(id,name, surname,email, phone));
        }
        return result;
    }

    public User getById(int userId) throws Exception{
        User result = null;

        Connection c = connect();
        Statement stmt = c.createStatement();
        stmt.execute("SELECT * FROM user where id="+userId);
        ResultSet rs = stmt.getResultSet();

        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String phone = rs.getString("phone");

            result = new User(id,name, surname,email, phone);
        }
        return result;
    }

    public boolean updateUser(User u) throws Exception{
        Connection c = connect();
        PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?, phone=?,email=? where id=?");
        stmt.setString(1, u.getName());
        stmt.setString(2, u.getSurname());
        stmt.setString(3, u.getPhone());
        stmt.setString(4, u.getEmail());
        stmt.setInt(5, u.getId());
        return stmt.execute();
    }

    public boolean removeUser(int id) throws Exception {

        Connection c = connect();
        Statement stmt = c.createStatement();
        stmt.execute("delete from user where id=1");
        return true;

    }



}
