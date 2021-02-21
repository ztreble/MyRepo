package com.neu.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EditDiscussionQueControllerRequest {
    @NotNull
    private int id;
    @NotBlank
    private String title;
    @NotBlank
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
