package com.wjc.imp;

import com.wjc.ReplyDao;
import com.wjc.pojo.Reply;
import com.wjc.utils.CRUDUtil;

public class ReplyDaoImp implements ReplyDao {
    @Override
    public int insertReply(Reply reply) {
        String sql = "insert into reply (user_id,question_id,reply)values(?,?,?)";
        return CRUDUtil.executeUpdate(sql,reply.getUser_id(),reply.getQuestion_id(),reply.getReply());

    }
}
