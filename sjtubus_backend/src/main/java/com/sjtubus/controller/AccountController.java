package com.sjtubus.controller;

import com.sjtubus.entity.User;
import com.sjtubus.model.response.HttpResponse;
import com.sjtubus.model.response.ProfileResponse;
import com.sjtubus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Action;

/**
 * @author Allen
 * @date 2018/7/13 10:49
 */

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public HttpResponse login(HttpServletRequest request,
                              @RequestParam("phone")String phone,
                              @RequestParam("password")String password){
        System.out.println("收到登陆请求！");
        HttpResponse response = new HttpResponse();
        if(phone == null || password == null){
            response.setError(1);
            response.setMsg("登陆信息不全！");
            return response;
        }
        User user = userService.findUserByPhone(phone);
        System.out.println("查找用户结束！");
        if(user == null || !user.getPassword().equals(password)){
            response.setError(1);
            response.setMsg("电话号码或密码不正确！");
            return response;
        }else {
            HttpSession session = request.getSession(true);
            //session过期时间为3天
            session.setMaxInactiveInterval(60*60*24*3);
            System.out.println("创建session！");
            session.setAttribute("user",user);
            response.setError(0);
            response.setMsg("登录成功！");
            return response;
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public HttpResponse register(HttpServletRequest request,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("username")String username,
                                 @RequestParam("password")String password){
        HttpResponse response = new HttpResponse();
        String token = request.getParameter("token");
        if(!token.equals("dks3824LHEBF92IUD2RG709")){
            response.setError(1);
            response.setMsg("缺少密钥!");
            return response;
        }
        User user = userService.findUserByPhone(phone);
        if(user!=null){
            response.setError(1);
            response.setMsg("该手机号已注册!");
            return response;
        }
        user = userService.addUser(username,password,false,phone,100);
        if(user==null){
            response.setError(1);
            response.setMsg("该用户名已被注册!");
            return response;
        }
        response.setError(0);
        response.setMsg("注册成功!");
        return response;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public HttpResponse logout(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        //销毁cookies
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        //销毁session
        if(session!=null) session.invalidate();
        HttpResponse response1 = new HttpResponse();
        response1.setMsg("成功登出!");
        response1.setError(0);
        return response1;
    }

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public ProfileResponse getProfile(HttpServletRequest request){
        ProfileResponse response = new ProfileResponse();
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            response.setMsg("未登录");
            response.setError(1);
            return response;
        }
        User user = (User)session.getAttribute("user");
        response.setUser(user);
        response.setError(0);
        response.setMsg("已登陆~"+user.getUsername());
        return response;
    }
}
