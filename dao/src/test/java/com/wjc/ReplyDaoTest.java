package com.wjc;

import com.wjc.imp.ReplyDaoImp;
import com.wjc.pojo.Reply;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReplyDaoTest {

    private ReplyDao replyDao;
    @Before
    public void setUp() throws Exception {
        replyDao = new ReplyDaoImp();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试ReplyDao");
    }

    @Test
    public void insertReply() {
        Reply reply = new Reply();
        reply.setUser_id(2);
        reply.setQuestion_id(6);
        reply.setReply("新世纪的青年");
        assertEquals(1,replyDao.insertReply(reply));
    }
}