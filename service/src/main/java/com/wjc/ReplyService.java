package com.wjc;

import com.wjc.pojo.Reply;

public interface ReplyService {
    Boolean insertReply(Reply reply);

    Reply findReply(long question_id);
}
