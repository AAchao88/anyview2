import com.wjc.UserDao;
import com.wjc.imp.UserDaoImp;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserDao userDao;
    private User user;
    @Before
    public void setUp() throws Exception {
        userDao = new UserDaoImp();
        user = new User();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试UserServiceImp");
    }

    @Test
    public void getPersonalInformation() {
        user.setUserNumber("123456");
        assertNotNull(userDao.getPersonalInformation(user));
        System.out.println(userDao.getPersonalInformation(user));
    }

    @Test
    public void login() {
        user.setUserNumber("1234561");
        user.setPassword("1234561");
        assertNull(userDao.findByUserNumberAndPassword(user));
    }
}