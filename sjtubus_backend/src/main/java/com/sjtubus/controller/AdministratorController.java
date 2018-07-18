package com.sjtubus.controller;

import com.sjtubus.entity.Administrator;
import com.sjtubus.model.response.HttpResponse;
import com.sjtubus.service.AdministratorService;
import com.sjtubus.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping(path = "/administrator")
public class AdministratorController {

    @Autowired
    AdministratorService administratorService;

    @RequestMapping(path = "/login")
    public HttpResponse processLogin(@RequestParam("username")String username,
                                     @RequestParam("password") String password,
                                      HttpSession session){
        HttpResponse response = new HttpResponse();
        try{
            List<Administrator> administratorList = administratorService.searchAdministrator(username);
            if (administratorList.size() == 0)
                response.setMsg("not exist");
            else if (!administratorList.get(0).getPassword().equals(password))
                response.setMsg("wrong password");
            else{
                Administrator administrator = administratorList.get(0);
                System.out.println("sessionid1:"+session.getId());
                session.setAttribute("administrator", administrator);
                System.out.println("reserved:"+((Administrator)session.getAttribute("administrator")).getUsername());
                response.setMsg("success");
            }
        }
        catch (Exception e){
            response.setMsg("fail");
            response.setError(1);
        }
        return response;
    }

    @RequestMapping(path = "/logout")
    public HttpResponse processLogout(HttpSession session){
        HttpResponse response = new HttpResponse();
        try {
            if (session.getAttribute("administrator") == null)
                response.setMsg("not logged");
            else{
                session.invalidate();
                response.setMsg("success");
            }
        }
        catch (Exception e){
            response.setMsg("fail");
            response.setError(1);
        }
        return response;
    }


    @RequestMapping(path = "/judgestate")
    public HttpResponse judgeState(HttpSession session){
        HttpResponse response = new HttpResponse();
        try {
            System.out.println("sessionId2:" + session.getId());
            if (session.getAttribute("administrator") != null)
                response.setMsg("logged");
            else
                response.setMsg("not logged");
        }
        catch (Exception e){
            response.setMsg("fail");
            response.setError(1);
        }
        return response;
    }


    @RequestMapping(path = "/register")
    public HttpResponse processRegister(@RequestParam("username")String username,
                                        @RequestParam("password") String password,
                                        HttpSession session){
        HttpResponse response = new HttpResponse();
        try{
            List<Administrator> administratorList = administratorService.searchAdministrator(username);
            if (administratorList.size() > 0)
                response.setMsg("existed");
            else{
                Administrator administrator = administratorService.saveAdministrator(username, password);
                session.setAttribute("administrator", administrator);
                response.setMsg("success");
            }
        }
        catch (Exception e){
            response.setMsg("fail");
            response.setError(1);
        }
        return response;
    }

}
