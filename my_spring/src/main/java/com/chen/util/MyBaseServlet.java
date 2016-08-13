package com.chen.util;

import com.chen.annocation.RequestMapping;
import com.chen.enums.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by CHEN on 2016/8/12.
 * 基础实现类
 */
public class MyBaseServlet extends HttpServlet {
    private static final String REDIRECT = "redirect:";
    private static final String FORWARD = "forward:";

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String url = req.getRequestURI();
        //获得所有的方法
        //假如没有二级路径或二级以上的路径就会跳到一级路径
        String requestURL = url.substring(url.lastIndexOf("/"));

        if (requestURL == null || "".equals(requestURL)) {
            //假如没有该方法 就要设置一个默认的方法
            requestURL = "";
        }

        try {

/*注释说明：说真的一开始就没打算玩这么大，没打算做那么多个注解，可是，好像上了贼船，只好做了。
            下面这段代码的意思是：找到某个注解，看看他的httpMethod是什么，然后就调用路径的方法，可是这样不够restful.

            //获得请求的http方式
            String httpMethod = req.getMethod();
//            Method[] methods=this.getClass().getMethods(methodStr,HttpServletRequest.class,HttpServletResponse.class);
            Method[] methods = this.getClass().getMethods();
            String returnURL = "/";
            for (Method m : methods) {
                //获得注解的内容
                RequestMapping mapping = m.getDeclaredAnnotation(RequestMapping.class);
                RequestMethod requestMethod = mapping.method();
                if (requestMethod==RequestMethod.valueOf(httpMethod)) {
                    returnURL = (String) m.invoke(this, req, resp);
                    break;
                }
            }*/
            //

            //获得请求的http方式
            String httpMethod = req.getMethod();
            //获得所有的方法
            Method[] methods = this.getClass().getMethods();
            String returnURL = "/";
            for (Method m : methods) {
                //获得注解的内容
                RequestMapping mapping = m.getDeclaredAnnotation(RequestMapping.class);
                //请求http方式
                RequestMethod requestMethod = mapping.method();
                //url路径
                String methodURL=mapping.value();

                if (requestMethod==RequestMethod.valueOf(httpMethod)&&requestURL.equals(methodURL)) {
                    returnURL = (String) m.invoke(this, req, resp);
                    break;
                }
            }


            //重定向
            if (returnURL.startsWith(REDIRECT)) {
                resp.sendRedirect(returnURL.substring(REDIRECT.length()));
                return;
            } else if (returnURL.startsWith(FORWARD)) {
                req.getRequestDispatcher(returnURL.substring(FORWARD.length())).forward(req, resp);
                return;
            } else {
                String rootURL = this.getClass().getResource("/").getPath();
                String realURL = InternalResourceViewResolver.getRealURL(rootURL, returnURL);
                req.getRequestDispatcher(realURL).forward(req, resp);
                return;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
