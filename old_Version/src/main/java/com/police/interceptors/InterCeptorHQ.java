/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.police.interceptors;

import com.police.controller.PoliceHQController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Monirul Islam
 */
public class InterCeptorHQ extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean login=PoliceHQController.checkSession();
        System.out.println("HQ interceptor...");
        System.out.println("url : "+request.getContextPath());
        if(login == true)
            return true;
        else
        {   
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
    }
}
