package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter{

    private UserSkill getUserSkill(ResultSet rs) throws Exception{
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);
    }

    public List<UserSkill> getAllSkillByUserId(int userId) throws Exception{
        List<UserSkill> result = new ArrayList();

        Connection c = connect();
        PreparedStatement stmt = c.prepareStatement("SELECT" +
                " u.*, " +
                " us.skill_id, " +
                " s.NAME AS skill_name, " +
                " us.power " +
                "FROM " +
                " user_skill us " +
                " LEFT JOIN USER u ON us.user_id = u.id " +
                " LEFT JOIN skill s ON us.skill_id = s.id " +
                " WHERE " +
                " us.user_id = ?");
        stmt.setInt(1, userId);
        stmt.execute();
        ResultSet rs = stmt.getResultSet();

        while (rs.next()){
            UserSkill u = getUserSkill(rs);

            result.add(u);
        }
        return result;
    }


}



