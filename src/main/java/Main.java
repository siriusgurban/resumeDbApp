import com.company.Context;
import com.company.bean.User;
import com.company.dao.impl.UserDaoImpl;
import com.company.dao.inter.UserDaoInter;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = Context.instanceUserDao();

        User u = userDao.getById(2);
        u.setName("Kuzeyir");
        userDao.updateUser(u);
    }

}