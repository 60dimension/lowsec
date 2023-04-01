package com.seckeep.controllers;

import com.seckeep.tool.Byte2obj;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 60dimension
 * @Date: 2022/4/11 4:16 PM
 */
@Controller
public class Unserializable
{
    @RequestMapping("/unser/index")
    public ModelAndView index()
    {
        ModelAndView index=new ModelAndView("/unser/index");
        return index;
    }

    @RequestMapping("/unser/exec")
    public  void aa(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String str=request.getParameter("exec");
        System.out.println(str);
        Byte2obj.toObject(Base64.decode(str));
    }
}
