package com.seckeep.controllers;

import com.seckeep.tool.MyObject;
import com.seckeep.tool.RandomCrack;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 60dimension
 * @Date: 2022/4/12 11:35 AM
 */
@Controller
public class Random
{

    @RequestMapping("/random/index")
    public ModelAndView index()
    {
        ModelAndView index=new ModelAndView("/random/index");
        return index;
    }
    @RequestMapping("/random/exec")
    public ModelAndView exec(HttpServletRequest request)
    {
        int num1= Integer.parseInt(request.getParameter("num1"));
        int num2= Integer.parseInt(request.getParameter("num2"));
        int num3;
        num3= RandomCrack.Crack(num1,num2);

        Map<String,Object> params=new HashMap<>();
        params.put("exec",num3);

        ModelAndView exec=new ModelAndView("/random/exec");
        exec.addAllObjects(params);
        return exec;
    }
}
