package com.chen.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Iterator;

import static com.sun.deploy.security.ruleset.DRSResult.Type.RUN;


/**
 * Created by CHEN on 2016/8/12.
 * 这是一个视图控制类
 * 主要做的就是从jsp_view.xml中获得路径前缀和后缀
 * 然后拼接，跳转
 */

public class InternalResourceViewResolver {

    public static String getRealURL(String rootURL,String url){
        SAXReader reader = new SAXReader();
        Document document = null;

        try {
            document=reader.read(new  File(rootURL,"jsp_view.xml"));
        } catch (DocumentException e) {
            System.out.println("文件不存在");
            e.printStackTrace();
        }
        if(null==document) {
            //TODO 很多代码
        } else {
            //开始读取xml
            String prefix=null;
            String suffix=null;

            //读取文档
            Element bean =document.getRootElement();
            Iterator<Element> iterator=bean.elementIterator("property");
            Element e=null;
            while(iterator.hasNext()) {
                e= iterator.next();
                if("prefix".equals(e.attributeValue("name"))) {
                    prefix=e.attributeValue("value");
                } else if("suffix".equals(e.attributeValue("name"))) {
                    suffix=e.attributeValue("value");
                }
            }
            StringBuffer string=new StringBuffer(prefix);
            string.append(url);
            string.append(suffix);
            return string.toString();
        }
        return "";
    }

}
