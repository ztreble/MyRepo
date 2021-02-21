package com.neu.entity;

import java.sql.Timestamp;
/**
* @author lzs
* @Date 2020/4/4 
*/

/**
 * 讨论 讨论-提问
 */
public class DiscussionQue
{
    /**
     * 讨论id
     */
    protected Integer id;
    /**
     * 作者id
     */
    protected Integer creatorId;
    /**
     * 话题id
     */
    protected Integer topicId;
    /**
     * 标题
     */
    protected String title;
    /**
     * 正文 正文 文章正文
     */
    protected String message;
    /**
     * 讨论回答数 讨论回答数 讨论回答数
     */
    protected Integer discussionAns;
    /**
     * 点赞数 点赞数 点赞数
     */
    protected Integer likes;
    /**
     * 思考数 思考数 思考数
     */
    protected Integer thinks;
    /**
     * 惊喜数 惊喜数 惊喜数
     */
    protected Integer surprises;
    /**
     * 发起时间
     */
    protected Timestamp initializeTime;
    /**
     * 编辑时间
     */
    protected Timestamp editTime;
    /**
     * 关注人数
     */
    protected Integer follows;
    /**
     * 参与人数
     */
    protected Integer joins;
    /**
     * 浏览人数
     */
    protected Integer browse;

    public DiscussionQue()
    {
    }

    /**
     * Get 讨论id
     */
    public Integer getId()
    {
        return id;
    }
    /**
     * Set 讨论id
     */
    public void setId(Integer value)
    {
        this.id = value;
    }
    /**
     * Get 作者id
     */
    public Integer getCreatorId()
    {
        return creatorId;
    }
    /**
     * Set 作者id
     */
    public void setCreatorId(Integer value)
    {
        this.creatorId = value;
    }
    /**
     * Get 话题id
     */
    public Integer getTopicId()
    {
        return topicId;
    }
    /**
     * Set 话题id
     */
    public void setTopicId(Integer value)
    {
        this.topicId = value;
    }
    /**
     * Get 标题
     */
    public String getTitle()
    {
        return title;
    }
    /**
     * Set 标题
     */
    public void setTitle(String value)
    {
        this.title = value;
    }
    /**
     * Get 正文 正文 文章正文
     */
    public String getMessage()
    {
        return message;
    }
    /**
     * Set 正文 正文 文章正文
     */
    public void setMessage(String value)
    {
        this.message = value;
    }
    /**
     * Get 讨论回答数 讨论回答数 讨论回答数
     */
    public Integer getDiscussionAns()
    {
        return discussionAns;
    }
    /**
     * Set 讨论回答数 讨论回答数 讨论回答数
     */
    public void setDiscussionAns(Integer value)
    {
        this.discussionAns = value;
    }
    /**
     * Get 点赞数 点赞数 点赞数
     */
    public Integer getLikes()
    {
        return likes;
    }
    /**
     * Set 点赞数 点赞数 点赞数
     */
    public void setLikes(Integer value)
    {
        this.likes = value;
    }
    /**
     * Get 思考数 思考数 思考数
     */
    public Integer getThinks()
    {
        return thinks;
    }
    /**
     * Set 思考数 思考数 思考数
     */
    public void setThinks(Integer value)
    {
        this.thinks = value;
    }
    /**
     * Get 惊喜数 惊喜数 惊喜数
     */
    public Integer getSurprises()
    {
        return surprises;
    }
    /**
     * Set 惊喜数 惊喜数 惊喜数
     */
    public void setSurprises(Integer value)
    {
        this.surprises = value;
    }
    /**
     * Get 发起时间
     */
    public Timestamp getInitializeTime()
    {
        return initializeTime;
    }
    /**
     * Set 发起时间
     */
    public void setInitializeTime(Timestamp value)
    {
        this.initializeTime = value;
    }
    /**
     * Get 编辑时间
     */
    public Timestamp getEditTime()
    {
        return editTime;
    }
    /**
     * Set 编辑时间
     */
    public void setEditTime(Timestamp value)
    {
        this.editTime = value;
    }
    /**
     * Get 关注人数
     */
    public Integer getFollows()
    {
        return follows;
    }
    /**
     * Set 关注人数
     */
    public void setFollows(Integer value)
    {
        this.follows = value;
    }
    /**
     * Get 参与人数
     */
    public Integer getJoins()
    {
        return joins;
    }
    /**
     * Set 参与人数
     */
    public void setJoins(Integer value)
    {
        this.joins = value;
    }
    /**
     * Get 浏览人数
     */
    public Integer getBrowse()
    {
        return browse;
    }
    /**
     * Set 浏览人数
     */
    public void setBrowse(Integer value)
    {
        this.browse = value;
    }
}

