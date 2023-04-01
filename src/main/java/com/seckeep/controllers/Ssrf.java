package com.seckeep.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 60dimension
 * @Date: 2022/4/11 3:35 PM
 */
@Controller
public class Ssrf
{
    @RequestMapping("/ssrf/index")
    public ModelAndView index()
    {
        ModelAndView shell=new ModelAndView("/ssrf/index");
        return shell;
    }

    @RequestMapping("/ssrf/parse")
    public ModelAndView ssrf(HttpServletRequest request)
    {
        String s;
        String a="";

        try
        {
            InputStream inputStream = null;
            String uri=request.getParameter("uri");
            URL url = new URL(uri); //
            URLConnection urlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((s = in.readLine()) != null) {
                a+=s+"<br>";
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }

        Map<String,Object> params=new HashMap<>();
        params.put("parse",a);
        ModelAndView index =new ModelAndView("/ssrf/parse");
        index.addAllObjects(params);
        return index;
    }

}
