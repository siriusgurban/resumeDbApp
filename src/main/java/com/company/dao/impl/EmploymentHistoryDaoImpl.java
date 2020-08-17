package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.EmploymentHistory;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmploymentHistoryDaoImpl extends AbstractDao implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception{
        String header = rs.getString("header");
        String jobDescription = rs.getString("job_description");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        int userId = rs.getInt("user_id");
        EmploymentHistory emp = new EmploymentHistory(null,header, beginDate, endDate, jobDescription,new User(userId));
        return emp;
    }

    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) throws Exception{
        List<EmploymentHistory> result = new ArrayList();

        Connection c = connect();
        PreparedStatement stmt = c.prepareStatement("SELECT * FROM employment_history WHERE user_id = ?");
        stmt.setInt(1, userId);
        stmt.execute();
        ResultSet rs = stmt.getResultSet();

        while (rs.next()){
            EmploymentHistory emp = getEmploymentHistory(rs);

            result.add(emp);
        }
        return result;
    }


}
