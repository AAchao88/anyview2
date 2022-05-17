package com.wjc;

import com.wjc.imp.UserDaoImp;
import com.wjc.pojo.User;
import com.wjc.utils.BeanHandler;
import com.wjc.utils.CRUDUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserDaoTest {

    private UserDao userDao;
    private User user;
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

    @Test
    public void getPersonalInformation() {
        user.setUserNumber("123456");
      //  user.setPassword("1234561");
        String sql = "select * from user where userNumber = ?";
        assertEquals("吴锦潮",CRUDUtil.executeQuery(sql,new BeanHandler<>(User.class),user.getUserNumber()).getUserName());

    }

    @Test
    public void getAllUserInClass() {
        String className = "21级计算机类7班";
        assertEquals(2,userDao.getAllUserInClass(className).size());
    }

    @Test
    public void findUserById() {
        user.setId(2);
        assertEquals("21级计算机类7班",userDao.findUserById(user.getId()).getClassName());
    }
}