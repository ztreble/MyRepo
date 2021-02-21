package com.neu.mapper;

import com.neu.entity.DiscussionQue;
import org.springframework.stereotype.Repository;
/**
* @author lzs
* @Date 2020/4/4
*/

@Repository
public interface DiscussionQueMapper {
    /**
     * 创建讨论
     */
    public void createDiscussionQue(DiscussionQue discussionQue);
    /**
     * 修改讨论
     */
    public int EditDiscussionQue(DiscussionQue discussionQue);
    /**
     * 获取讨论by_id
     */
    public DiscussionQue getDiscussionQueById(int id);
    /**
     * 删除讨论
     */
    public int delDiscussionQueById(int id);
}
