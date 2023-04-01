package com.seckeep.models;


import org.springframework.web.util.HtmlUtils;

import java.util.Date;

/**
 * Created by 60dimension on 16/3/17.
 */
public class Mpost {
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return HtmlUtils.htmlEscape(content);
        //return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private int pid;
    private String title;
    private String content;
    private Date date;
    private String username;


}
