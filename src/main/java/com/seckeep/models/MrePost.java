package com.seckeep.models;

import java.util.Date;

/**
 * Created by 60dimension on 16/3/17.
 */
public class MrePost {
    private int pid;
    private int rid;
    private String content;
    private Date date;
    private String username;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getContent() {
        //return HtmlUtils.htmlEscape(content);
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
