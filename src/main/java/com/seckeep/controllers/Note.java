package com.seckeep.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: 60dimension
 * @Date: 2022/4/12 1:49 PM
 */
@Controller
public class Note
{
    @RequestMapping("/note/index")
    public ModelAndView index()
    {
        ModelAndView index=new ModelAndView("/note/index");
        return index;
    }
}
