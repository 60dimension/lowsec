package com.seckeep.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 60dimension
 * @Date: 2022/4/8 2:12 PM
 */

@Controller
public class Upshell
{
    @RequestMapping("/upshell/index")
    public ModelAndView index(){
        ModelAndView shell=new ModelAndView("/upshell/index");
        return shell;
    }


    @RequestMapping("/upshell/upload")//Upload File
    public ModelAndView Upload(MultipartFile file) throws IOException
    {
        if (file != null)
        {
            file.transferTo(new File("/Users/seckeep/IdeaProjects/lowsec/src/main/java/com/seckeep/controllers"+"/"+file.getOriginalFilename()));
        }

        ModelAndView upload=new ModelAndView("/upshell/upload");
        Map<String,Object> p=new HashMap<>();
        p.put("upload","木马地址：http://localhost:8080/shell");
        upload.addAllObjects(p);
        return upload;
    }


}
