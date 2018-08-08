/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.police.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Monirul Islam
 */
@Controller
public class PublicUserController {
    
    public static void getUserCredential(String email,String encryptedPassw)
    {
        System.out.println("Pub"+email);
        System.out.println("Pubpas"+encryptedPassw);
    }
    
    @RequestMapping(value = "PublicUser/dashBoard")
    public String showDash()
    {
        System.out.println("PubCon Got Call......");
        return "PublicUser/dashBoard";
    }
}
