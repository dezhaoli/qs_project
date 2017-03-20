package com.qs.common.util;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class XmlUtil {
   
	
    public static String readXml(String fileName) throws Exception {  
        //创建saxreader对象  
        SAXReader reader = new SAXReader();  
        //读取一个文件，把这个文件转换成Document对象  
        Document document = reader.read(new File(fileName));  
        //获取根元素  
        Element root = document.getRootElement();  
        //把文档转换字符串  
        String docXmlText = document.asXML();  
        return docXmlText;
  
    }  
    
}
