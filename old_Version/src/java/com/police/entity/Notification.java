package com.police.entity;
// Generated 23-Jul-2017 02:07:18 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Notification generated by hbm2java
 */
@Entity
@Table(name="NOTIFICATION"
    ,schema="MONIR"
)
public class Notification  implements java.io.Serializable {


     private BigDecimal id;
     private String district;
     private String division;
     private String caseId;
     private String generator;
     private String createDate;
     private String message;

    public Notification() {
    }

	
    public Notification(BigDecimal id) {
        this.id = id;
    }
    public Notification(BigDecimal id, String district, String division, String caseId, String generator, String createDate, String message) {
       this.id = id;
       this.district = district;
       this.division = division;
       this.caseId = caseId;
       this.generator = generator;
       this.createDate = createDate;
       this.message = message;
    }
   
      @Id 
     @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="NOTIFICATION_SEQ")
    @SequenceGenerator(name="NOTIFICATION_SEQ", sequenceName="NOTIFICATION_SEQ",allocationSize=1)
    @Column(name="ID", unique=true, nullable=false, precision=20, scale=0)
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }

    
    @Column(name="DISTRICT", length=100)
    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }

    
    @Column(name="DIVISION", length=100)
    public String getDivision() {
        return this.division;
    }
    
    public void setDivision(String division) {
        this.division = division;
    }

    
    @Column(name="CASE_ID", length=20)
    public String getCaseId() {
        return this.caseId;
    }
    
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    
    @Column(name="GENERATOR", length=100)
    public String getGenerator() {
        return this.generator;
    }
    
    public void setGenerator(String generator) {
        this.generator = generator;
    }

    
    @Column(name="CREATE_DATE", length=100)
    public String getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    
    @Column(name="MESSAGE", length=200)
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }




}


