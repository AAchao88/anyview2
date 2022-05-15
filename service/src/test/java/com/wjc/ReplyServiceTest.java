package com.wjc;

import com.wjc.imp.ReplyServiceImp;
import com.wjc.pojo.Reply;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReplyServiceTest {

    private ReplyService replyService;
    @Before
    public void setUp() throws Exception {
        replyService = new ReplyServiceImp();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试ReplyService");
    }

    @Test
    public void insertReply() {
        Reply reply = new Reply();
        reply.setUser_id(2);
        reply.setQuestion_id(5);
        reply.setReply("很多种变换");
        assertTrue(replyService.insertReply(reply));
    }

    @Test
    public void findReply() {
        long id = 6;
        assertEquals("新世纪的青年",replyService.findReply(id).getReply());
    }
}