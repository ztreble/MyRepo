package com.neu.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* @author lzs
* @Date 2020/4/4
*/

public class CreateDiscussionQueRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String message;
    @NotNull
    private int topicId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}
