package com.seckeep.controllers;

import com.seckeep.models.Mpost;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 60dimension on 16/3/27.
 */

@Controller
public class CPost extends Default
{
    @Resource(name = "mybatisTemplate")
    private SqlSessionTemplate postsql;

    @RequestMapping("/index")
    public ModelAndView index()
    {

        List<Mpost> post=postsql.selectList("post.select");
        Map<String,Object> params=new HashMap<>();
        params.put("posts",post);
        ModelAndView index=new ModelAndView("/post/index");
        index.addAllObjects(params);
        return index;
    }

    @RequestMapping("/post/add")
    public ModelAndView add()
    {
        ModelAndView add=new ModelAndView("/post/add");
        return add;
    }

    @RequestMapping("post/insert")
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response)
    {

        String title=request.getParameter("title");
        String content=request.getParameter("content");
        String username=(String)request.getSession().getAttribute("username");


        if(request.getSession().getAttribute("username")!=null)
        {
            Mpost p=new Mpost();
            p.setTitle(title);
            p.setContent(content);
            p.setUsername(username);
            postsql.insert("post.insert",p);
            if(p.getPid()>0)
            {
                try {
                    response.sendRedirect("/index");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            ModelAndView login=new ModelAndView("/user/login");
            return login;
        }
        return null;
    }

    @RequestMapping("post/del")
    public ModelAndView del(HttpServletRequest request,HttpServletResponse response)
    {
        int pid=Integer.parseInt(request.getParameter("pid"));
        Map<String,Integer> parampid=new HashMap<>();
        parampid.put("pid",pid);
        List<Mpost> find=postsql.selectList("post.find",parampid);
        String userA=find.get(0).getUsername();
        String userB=(String)request.getSession().getAttribute("username");
        if(userA.equals(userB))
        {
            postsql.delete("post.delete",parampid);
            try {
                response.sendRedirect("/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Map<String ,String> params=new HashMap<>();
            params.put("info","未登录或尝试删除他人发的帖子.");
            ModelAndView info=new ModelAndView("/public/info");
            info.addAllObjects(params);
            return info;
        }

        return null;
    }

    @RequestMapping("post/edit")
    public ModelAndView edit(HttpServletRequest request)
    {
        String pid= request.getParameter("pid");
        Map<String,String  > parampid=new HashMap<>();
        parampid.put("pid",pid);

        List<Mpost> find=postsql.selectList("post.find",parampid);
        Map<String,Object> params=new HashMap<>();
        params.put("post",find);
        ModelAndView edit=new ModelAndView("/post/edit");
        edit.addAllObjects(params);
        return edit;
    }

    @RequestMapping("post/update")
    public ModelAndView update(HttpServletRequest request,HttpServletResponse response)
    {
        int pid= Integer.parseInt(request.getParameter("pid"));

        Map<String,Integer> parampid=new HashMap<>();
        parampid.put("pid",pid);
        List<Mpost> find=postsql.selectList("post.find",parampid);
        String userA=find.get(0).getUsername();
        String userB=(String)request.getSession().getAttribute("username");
        if(userA.equals(userB))
        {
            String title=request.getParameter("title");
            String content=request.getParameter("content");
            Mpost p=new Mpost();
            p.setPid(pid);
            p.setTitle(title);
            p.setContent(content);
            postsql.update("post.update",p);
            if(p.getPid()>0)
            {
                try {
                    response.sendRedirect("/index");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            Map<String ,String> params=new HashMap<>();
            params.put("info","未登录或尝试修改他人发的帖子");
            ModelAndView info=new ModelAndView("/public/info");
            info.addAllObjects(params);
            return info;
        }


        return null;
    }

    @RequestMapping("/post/search")
    public ModelAndView index(HttpServletRequest request)
    {
        String search=request.getParameter("search");
        Mpost p=new Mpost();
        p.setTitle(search);
        List<Mpost> find=postsql.selectList("post.search",p);
        Map<String,Object> params=new HashMap<>();
        params.put("posts",find);
        ModelAndView index =new ModelAndView("/post/index");
        index.addAllObjects(params);
        return index;
    }

    @RequestMapping("/post/secsearch")
    public ModelAndView secindex(HttpServletRequest request)
    {
        String search=request.getParameter("search");
        Mpost p=new Mpost();
        p.setTitle(search);
        List<Mpost> find=postsql.selectList("post.secsearch",p);
        Map<String,Object> params=new HashMap<>();
        params.put("posts",find);
        ModelAndView index =new ModelAndView("/post/index");
        index.addAllObjects(params);
        return index;
    }



}