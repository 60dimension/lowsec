package com.seckeep.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 60dimension
 * @Date: 2022/4/7 4:50 PM
 */

@Controller
public class Csrf
{
    @RequestMapping("/csrf/index")
    public ModelAndView index()
    {
        ModelAndView index=new ModelAndView("/csrf/index");
        return index;
    }


}
