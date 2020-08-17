package com.company.dao.inter;

import com.company.entity.User;
import com.company.entity.UserSkill;

import java.util.List;

public interface UserDaoInter {

    public List<User> getAll() throws Exception;

    public User getById(int id) throws Exception;

    public boolean updateUser(User u) throws Exception;

    public boolean addUser(User u) throws Exception;

    public boolean removeUser(int id) throws Exception;



}
