/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.police.controller;

import com.police.entity.CaseDocuments;
import com.police.entity.CaseRecord;
import com.police.entity.PoliceStationUser;
import com.police.model.PolicePSModel;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Monirul Islam
 */
@Controller
public class PoliceStationController {

    static HttpSession session;
    PolicePSModel psModel = new PolicePSModel();

    public static void craeteLoginSession(PoliceStationUser user, HttpServletRequest req) {
        System.out.println("Session Created");
        user.setPassword("null");
        session = req.getSession();
        session.setAttribute("ps", user.getPsName());
        session.setAttribute("user", user);

    }

    public static boolean checkSession() {
        boolean login = false;
        PoliceStationUser user;
        try {
            user = (PoliceStationUser) session.getAttribute("user");
            if (user.getRole().equals("PS_ADMIN")) {
                login = true;
            } else {
                login = false;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            login = false;
        }

        return login;
    }

    @RequestMapping(value = "PoliceStation/dashBoard")
    public String viewDash() {
        return "PoliceStation/dashBoard";
    }

    @RequestMapping(value = "PoliceStation/newCase", method = RequestMethod.GET)
    public String viewCaseInfoForm() {

        return "PoliceStation/newCase";
    }

    @RequestMapping(value = "PoliceStation/newCase", method = RequestMethod.POST)
    public String insertCaseInfo(@ModelAttribute(value = "CaseRecord") CaseRecord newCase, Model model) {

        PoliceStationUser user = (PoliceStationUser) session.getAttribute("user");
        System.out.println("Criem : " + newCase.getCrimeDate().toString());
        System.out.println("Criem : " + newCase.getCaseFileDate().toString());
        newCase.setPoliceStation(user.getPsName());
        newCase.setDistrict(user.getDistrict());
        newCase.setDivision(user.getDivision());
        newCase.setCaseId("C" + psModel.getCaseRecordMaxId().add(BigDecimal.ONE));
        System.out.println("case id :" + newCase.getCaseId());
        boolean insert = psModel.insertNewCase(newCase);
        System.out.println("insert val : " + insert);
        if (insert == true) {
            System.out.println("saodhashdhasihdiaosh");
            model.addAttribute("caseInfo", newCase);
            return "PoliceStation/CaseManagement";
        } else {
            return "PoliceStation/newCase";
        }
    }

    @RequestMapping(value = "PoliceStation/caseList", method = RequestMethod.GET)
    public String viewCaseList(Model m) {
        PoliceStationUser user = (PoliceStationUser) session.getAttribute("user");
        String sDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<CaseRecord> caseList = psModel.getPSCaseList(user.getPsName(), new Date());
        m.addAttribute("caseList", caseList);
        m.addAttribute("sDate", sDate);
        return "PoliceStation/caseList";
    }

    @RequestMapping(value = "PoliceStation/caseList", method = RequestMethod.POST)
    public String viewCaseListToDate(Model model, @RequestParam(value = "searchDate") String date) {
        PoliceStationUser user = (PoliceStationUser) session.getAttribute("user");

        Date searchDate=new Date();
        
        System.out.println("searchDate :  " + searchDate);
        try{
            searchDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        String sDate = new SimpleDateFormat("yyyy-MM-dd").format(searchDate);
        List<CaseRecord> caseList = psModel.getPSCaseList(user.getPsName(), searchDate);
        model.addAttribute("caseList", caseList);
        model.addAttribute("sDate", sDate);
        return "PoliceStation/caseList";
    }
    
    @RequestMapping(value = "PoliceStation/CaseManagement")
    public String viewCaseManageMent(@RequestParam(value = "caseId") String caseId,Model model)
    {
        CaseRecord caseObj=psModel.getCaseRecordByCaseId(caseId);
        List<CaseDocuments> caseDocList=psModel.getCaseDocByCaseId(caseId);
        System.out.println("Doc list Size : "+caseDocList.size());
        model.addAttribute("caseDocList", caseDocList);
        model.addAttribute("caseObj", caseObj);
        return "PoliceStation/CaseManagement";
    }
    
    @RequestMapping(value = "addCaseDoc",method = RequestMethod.POST)
    public String addCaseDoc(@ModelAttribute(value = "CaseDocuments") CaseDocuments CaseDoc )
    {
        return "PoliceStation/CaseManagement";
    }
    
    @RequestMapping(value = "PoliceStation/uploadCaseFile",method = RequestMethod.POST)
    public @ResponseBody String uploadCaseFile(@RequestParam(value = "file1") MultipartFile file1,
                                                @RequestParam(value = "fileName") String fileName,
                                                @RequestParam(value = "caseId") String caseId,
                                                @RequestParam(value = "fileType") String fileType,
                                                HttpServletRequest req)
    {
        String fileN = file1.getOriginalFilename();
        
        System.out.println("Cont nam : "+fileN);
        System.out.println("File name : "+fileName);
        String rootPath = System.getProperty("user.dir")+"\\"+req.getContextPath();
        System.out.println("Root : "+req.getContextPath());
        try{
            BufferedOutputStream outputStream = new BufferedOutputStream(
               new FileOutputStream(
                     new File(rootPath+"\\webapp\\resources\\case_documents", file1.getOriginalFilename())));
         outputStream.write(file1.getBytes());
         outputStream.flush();
         outputStream.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return "Successfully uploaded...";
    }
}
