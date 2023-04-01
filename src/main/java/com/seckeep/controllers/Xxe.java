package com.seckeep.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 60dimension
 * @Date: 2022/4/11 3:35 PM
 */
@Controller
public class Xxe
{
    @RequestMapping("/xxe/index")
    public ModelAndView index()
    {
        ModelAndView shell=new ModelAndView("/xxe/index");
        return shell;
    }


    @RequestMapping("/xxe/parse")
    public ModelAndView xxe(MultipartFile file)
    {
        String a="";
        DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();

        if (!file.isEmpty())
        {
            try
            {
                DocumentBuilder domBuilder = domfac.newDocumentBuilder();
                Document doc = (Document) domBuilder.parse(file.getInputStream());
                Element root = doc.getDocumentElement();
                NodeList books = root.getChildNodes();
                if (books != null)
                {
                    for (int i = 0; i < books.getLength(); i++)
                    {
                        Node book = books.item(i);
                        if (book.getNodeType() == Node.ELEMENT_NODE)
                        {
                            for (Node node = book.getFirstChild(); node != null; node = node.getNextSibling())
                            {
                                if (node.getNodeType() == Node.ELEMENT_NODE)
                                {
                                    if (node.getNodeName().equals("title"))
                                    {
                                        String k = node.getNodeValue();
                                        String s = node.getFirstChild().getNodeValue();
                                        a+=k+"</br>";
                                        a+=s+"</br>";
                                    }
                                    if (node.getNodeName().equals("doc"))
                                    {
                                        String price = node.getFirstChild().getNodeValue();
                                        a+=price+"</br>";
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Map<String,Object> params=new HashMap<>();
        params.put("parse",a);
        ModelAndView index =new ModelAndView("/xxe/parse");
        index.addAllObjects(params);
        return index;
    }
}
