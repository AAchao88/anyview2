import com.wjc.UserDao;
import com.wjc.imp.UserDaoImp;
import com.wjc.pojo.User;
import com.wjc.utils.BeanHandler;
import com.wjc.utils.CRUDUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    private UserDao userDao;
    private User user;
    //private String sql;
    @Before
    public void setUp() throws Exception {
        userDao = new UserDaoImp();
        user = new User();

    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试UserDaoImp");
    }

    @Test
    public void findByUserNumberAndPassword() {
        user.setUserNumber("1234561");
        user.setPassword("1234561");
        String sql = "select * from user where userNumber = ? and password = ?";
        assertNull(CRUDUtil.executeQuery(sql, new BeanHandler<>(User.class),user.getUserNumber(),user.getPassword()));
    }
}