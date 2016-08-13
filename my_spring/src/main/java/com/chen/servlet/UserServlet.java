package com.chen.servlet;

import com.chen.annocation.RequestMapping;
import com.chen.enums.RequestMethod;
import com.chen.util.MyBaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by CHEN on 2016/8/12.
 */
@WebServlet("/user/*")
public class UserServlet extends MyBaseServlet{

    /**
     * 必须要有的方法，展示用户
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method= RequestMethod.GET,value="/user")
   public String getUser(HttpServletRequest req,HttpServletResponse resp) {

        return "user/user_home";
   }

    /**
     * 使用表单提交的方法
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method= RequestMethod.POST,value="/login")
    public String postLogin(HttpServletRequest req,HttpServletResponse resp) {
        //TODO 很多代码
        System.out.println("转发");
        //return "redirect:user";//这句的作用和下面一句的作用是一样的
        return "redirect:user";
    }

    @RequestMapping(method=RequestMethod.GET,value="/login")
    public String getLogin(HttpServletRequest req,HttpServletResponse resp) {
        //TODO 很多代码
        System.out.println("重定向");
        return "forward:user";
    }

    @RequestMapping(method=RequestMethod.GET,value="/login_page")
    public String getLoginPage(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 很多代码
        System.out.println("直接跳转");
        return "user/user_home";
    }




}
