import com.wjc.imp.UserDaoImp;
import com.wjc.imp.UserServiceImp;
import com.wjc.pojo.ResultInfo;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServletTest {

    private User user;
    private UserServiceImp userServiceImp;
    private UserDaoImp userDaoImp;
    private ResultInfo resultInfo;

    @Before
    public void setUp() throws Exception {
         user = new User();
         userDaoImp = new UserDaoImp();
         userServiceImp = new UserServiceImp();
         resultInfo = new ResultInfo();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after");
    }

    @Test
    public void login() {

    }
}