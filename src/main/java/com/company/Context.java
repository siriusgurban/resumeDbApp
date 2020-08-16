package com.company;

import com.company.dao.impl.UserDaoImpl;
import com.company.dao.inter.UserDaoInter;

public class Context {
    public static UserDaoInter instanceUserDao(){
        return new UserDaoImpl();
    }
}
