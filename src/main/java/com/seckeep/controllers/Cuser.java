package com.seckeep.controllers;

import com.seckeep.tool.Md5c;
import com.seckeep.models.Muser;
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
 * Created by 60dimension on 16/3/16.
 */
@Controller
public class Cuser extends Default{

    @Resource(name = "mybatisTemplate")
    private SqlSessionTemplate crtMybatisTemplate;

    @RequestMapping("/userinfo")
    public ModelAndView userselect(HttpServletRequest request,HttpServletResponse response){
        //Directly use the parameters submitted by the user as query criteria.
         int uid = Integer.parseInt(request.getParameter("uid"));
        //Use the authentication parameters in the session as the query criteria.
        //int uid=(Integer)request.getSession().getAttribute("uid");
        Muser u=new Muser();
        u.setUid(uid);
        List<Muser> select=crtMybatisTemplate.selectList("user.selectuser",u);
        ModelAndView selects=new ModelAndView("/user/userinfo");
        Map<java.lang.String, Object> params = new HashMap<java.lang.String, Object>();
        params.put("userinfo", select);
        selects.addAllObjects(params);
        return selects;
    }

    @RequestMapping("/user/reg")
    public ModelAndView userreg(){
        ModelAndView reg=new ModelAndView("/user/reg");
        return reg;
    }


    @RequestMapping("/user/login")
    public ModelAndView userlogin(){
        ModelAndView login=new ModelAndView("/user/login");
        return login;
    }

    @RequestMapping("/user/insert")
    public ModelAndView useradd(HttpServletRequest request, HttpServletResponse response){
        String username=request.getParameter("username");
        String password= Md5c.Md5(request.getParameter("password"));
        Muser us=new Muser();
        us.setUsername(username);
        Muser find=crtMybatisTemplate.selectOne("user.find",us);
        if(find==null)
        {
            Muser u =new Muser();
            u.setUsername(username);
            u.setPassword(password);
            crtMybatisTemplate.insert("user.insertuser",u);
            if(u.getUid()>0)
            {
                try {
                    response.sendRedirect("/user/login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            ModelAndView info=new ModelAndView("/public/info");
            Map<java.lang.String, java.lang.String> parms=new HashMap<java.lang.String, java.lang.String>();
            parms.put("info","User exist");
            info.addAllObjects(parms);
            return info;
        }
        return null;
    }


    @RequestMapping("/user/auth")
    public ModelAndView userauth(HttpServletRequest request,HttpServletResponse response)
    {
        java.lang.String username=request.getParameter("username");
        java.lang.String password=Md5c.Md5(request.getParameter("password"));
        Muser u=new Muser();
        u.setPassword(password);
        u.setUsername(username);
        List<Muser> login=crtMybatisTemplate.selectList("user.login",u);

        if(login!=null)
        {
            request.getSession().setAttribute("uid",login.get(0).getUid());
            request.getSession().setAttribute("username",login.get(0).getUsername());
        }
        else
        {

            System.out.print("error");
            /*ModelAndView info=new ModelAndView("/public/info");
            Map<String,String> parms=new HashMap<>();
            parms.put("info","useranme or password were error");
            info.addAllObjects(parms);
            return info;*/
        }
        ModelAndView index=new ModelAndView("/post/index");
        //System.out.println("====="+request.getSession().getAttribute("username"));
        return index;
    }


    @RequestMapping("/user/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response)
    {
        request.getSession().invalidate();
        ModelAndView index=new ModelAndView("/post/index");
        return index;
    }



}

