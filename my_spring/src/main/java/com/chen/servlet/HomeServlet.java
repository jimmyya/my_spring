package com.chen.servlet;

import com.chen.annocation.RequestMapping;
import com.chen.enums.RequestMethod;
import com.chen.util.MyBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by CHEN on 2016/8/12.
 */
@WebServlet("/home/*")
public class HomeServlet extends MyBaseServlet {

    /**
     * 获得登录页
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method= RequestMethod.GET,value="/home")
    public String getLoginPage(HttpServletRequest req,HttpServletResponse resp) {
        return "home/user_login";
    }




}
