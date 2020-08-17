import com.company.Context;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;

public class Main {

    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = Context.instanceUserDao();

        EmploymentHistoryDaoInter empDao = Context.instanceEmploymentHistoryDao();
        
        System.out.println(empDao.getAllEmploymentHistoryByUserId(1));

    }

}