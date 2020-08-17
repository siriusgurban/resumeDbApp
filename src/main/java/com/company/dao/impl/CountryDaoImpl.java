package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {

    public List<Country> getAll() throws Exception {
        return null;
    }
}



