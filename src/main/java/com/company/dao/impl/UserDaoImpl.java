package com.company.dao.impl;

import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthPlaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");

        System.out.println("id="+id);
        System.out.println("name="+name);
        System.out.println("surname="+surname);
        System.out.println("email="+email);
        System.out.println("phone="+phone);

        Country nationality = new Country(nationalityId, nationalityStr, null);
        Country birthplace = new Country(birthplaceId, birthPlaceStr, null);

        return new User(id,name, surname,email, phone, birthdate, nationality, birthplace);
    }

    private UserSkill getUserSkill(ResultSet rs) throws Exception{
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);
    }

    public List<User> getAll() throws Exception{
        List<User> result = new ArrayList();

        Connection c = connect();
        Statement stmt = c.createStatement();
        stmt.execute("SELECT " +
                "u.*, " +
                "n.NATIONALITY AS nationality," +
                "c.name AS birthplace " +
                "FROM " +
                "USER u " +
                "LEFT JOIN country n ON u.nationality_id = n.id " +
                "LEFT JOIN country c ON u.birthplace_id = c.id");
        ResultSet rs = stmt.getResultSet();

        while (rs.next()){
            User u = getUser(rs);

            result.add(u);
        }
        return result;
    }

    public User getById(int userId) throws Exception{
        User result = null;

        Connection c = connect();
        Statement stmt = c.createStatement();
        stmt.execute("SELECT " +
                "u.*, " +
                "n.NATIONALITY AS nationality," +
                "c.name AS birthplace " +
                "FROM " +
                "USER u " +
                "LEFT JOIN country n ON u.nationality_id = n.id " +
                "LEFT JOIN country c ON u.birthplace_id = c.id"+userId);
        ResultSet rs = stmt.getResultSet();

        while (rs.next()){
            result = getUser(rs);
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

    public boolean addUser(User u) throws Exception {
        Connection c = connect();
        PreparedStatement stmt = c.prepareStatement("insert into user(name,surname, phone,email) values(?,?,?,?)");
        stmt.setString(1, u.getName());
        stmt.setString(2, u.getSurname());
        stmt.setString(3, u.getPhone());
        stmt.setString(4, u.getEmail());
        return stmt.execute();
    }

    public boolean removeUser(int id) throws Exception {

        Connection c = connect();
        Statement stmt = c.createStatement();
        stmt.execute("delete from user where id=1");
        return true;

    }

}
