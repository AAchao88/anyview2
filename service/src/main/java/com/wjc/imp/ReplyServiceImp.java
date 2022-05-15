package com.wjc.imp;

import com.wjc.ReplyDao;
import com.wjc.ReplyService;
import com.wjc.pojo.Reply;

public class ReplyServiceImp implements ReplyService {
    @Override
    public Boolean insertReply(Reply reply) {
        ReplyDao replyDao = new ReplyDaoImp();
        if (replyDao.insertReply(reply) == 1){
            return true;
        }else {
            return false;
        }
    }
}
