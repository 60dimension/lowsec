package com.seckeep.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Command  extends Default
{
    @RequestMapping("/command/index")
    public ModelAndView index()
    {
        ModelAndView index=new ModelAndView("/command/index");
        return index;
    }

    //command execute
    @RequestMapping("/command/exec")
    public ModelAndView pd(HttpServletRequest request)
    {
        String s;
        String a="";
        Process p;
        String addr=request.getParameter("addr");
        String pingCommand = "ping "+"-c2 " + addr ;
        try
        {
            p = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",pingCommand});
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
            {
                a+=s+"<br>";
            }
            p.waitFor();
            p.destroy();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Map<String,Object> params=new HashMap<>();
        params.put("exec",a);
        ModelAndView index =new ModelAndView("/command/exec");
        index.addAllObjects(params);
        return index;
    }
}
