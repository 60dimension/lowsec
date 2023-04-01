package com.seckeep.controllers;

import com.seckeep.models.Mpost;
import com.seckeep.models.MrePost;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 60dimension on 16/3/27.
 */

@Controller
public class CrePost extends Default
{
    @Resource(name = "mybatisTemplate")
    private SqlSessionTemplate postsql;

    @RequestMapping("/repost/index")
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response)
    {
        response.setHeader("SET-COOKIE", "wctk="+request.getRequestedSessionId());
        int pid= Integer.parseInt(request.getParameter("pid"));
        Mpost post=new Mpost();
        post.setPid(pid);
        List<Mpost> find=postsql.selectList("post.find",post);
        Map<String,Object> params=new HashMap<>();
        params.put("post",find);

        MrePost rePost=new MrePost();
        rePost.setPid(pid);
        List<MrePost> repostList=postsql.selectList("repost.repostList",rePost);
        params.put("repostList", repostList);
        ModelAndView index =new ModelAndView("/repost/index");
        index.addAllObjects(params);
        return index;
    }

    @RequestMapping("/repost/add")
    public ModelAndView add(HttpServletRequest request)
    {
        int pid=Integer.parseInt(request.getParameter("pid"));
        Map<String ,Object>params=new HashMap<>();
        params.put("pid",pid);
        ModelAndView add=new ModelAndView("/repost/add");
        add.addAllObjects(params);
        return add;
    }

    @RequestMapping("repost/insert")
    public ModelAndView insert(HttpServletRequest request,HttpServletResponse response)
    {
        String auth=(String) request.getSession().getAttribute("username");
        if(StringUtils.isBlank(auth)){
            Map<String ,String> params=new HashMap<>();
            params.put("info","未登录.");
            ModelAndView info=new ModelAndView("/public/info");
            info.addAllObjects(params);
            return info;
        }
        int pid=Integer.parseInt(request.getParameter("pid"));
        String username=(String)request.getSession().getAttribute("username");
        String content=request.getParameter("content");
        MrePost p=new MrePost();
        p.setPid(pid);
        p.setUsername(username);
        p.setContent(content);
        postsql.insert("repost.insert",p);
        if(p.getRid()>=0)
        {
            try {
                response.sendRedirect("/repost/index?pid="+pid);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
