/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.police.model;

import com.police.entity.CaseDocuments;
import com.police.entity.CaseRecord;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Monirul Islam
 */
public class PolicePSModel {
    SessionFactory sf = HibernateUtil.getSessionFactory();
    
    
    public BigDecimal getCaseRecordMaxId()
    {
        BigDecimal maxxId;
        Session session = sf.openSession();
        
        try{
            session.beginTransaction();
            Criteria crt=session.createCriteria(CaseRecord.class);
            crt.setProjection(Projections.max("id"));
            maxxId =(BigDecimal)crt.uniqueResult();
            System.out.println("Max ID : "+maxxId);
        }catch(Exception e)
        {
            e.printStackTrace();
            maxxId=BigDecimal.ZERO;
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        
        return maxxId;
    }
    
    public boolean insertNewCase(CaseRecord newCase)
    {
        boolean done=false;
        Session session=sf.openSession();
        
        try{
            session.beginTransaction();
            session.save(newCase);
            session.getTransaction().commit();
            done=true;
        }catch(Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return done;
    }
    
    public List<CaseRecord> getPSCaseList(String PSname,Date date)
    {
        List<CaseRecord> caseList=new ArrayList();
        Session session=sf.openSession();
        
        try{
            session.beginTransaction();
            Criteria crt=session.createCriteria(CaseRecord.class);
            crt.add(Restrictions.eq("policeStation", PSname));
            crt.add(Restrictions.eq("caseFileDate", date));
            caseList=crt.list();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return caseList;
    }
    
    public CaseRecord getCaseRecordByCaseId(String CaseID)
    {
        CaseRecord caseObj=new CaseRecord();
        Session session=sf.openSession();
        try{
            Criteria crt=session.createCriteria(CaseRecord.class);
            crt.add(Restrictions.eq("caseId", CaseID));
            caseObj=(CaseRecord) crt.uniqueResult();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return caseObj;
    }
    
    public List<CaseDocuments> getCaseDocByCaseId(String caseId)
    {
        List<CaseDocuments> caseDocList=new ArrayList();
        Session session=sf.openSession();
        try{
            Criteria crt=session.createCriteria(CaseDocuments.class);
            crt.add(Restrictions.eq("caseId", caseId));
            caseDocList=crt.list();
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return caseDocList;
    }
}
