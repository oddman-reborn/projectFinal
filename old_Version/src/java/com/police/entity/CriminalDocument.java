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
 * CriminalDocument generated by hbm2java
 */
@Entity
@Table(name="CRIMINAL_DOCUMENT"
    ,schema="MONIR"
)
public class CriminalDocument  implements java.io.Serializable {


     private BigDecimal id;
     private String criminalId;
     private String name;
     private String path;
     private String type;

    public CriminalDocument() {
    }

	
    public CriminalDocument(BigDecimal id) {
        this.id = id;
    }
    public CriminalDocument(BigDecimal id, String criminalId, String name, String path, String type) {
       this.id = id;
       this.criminalId = criminalId;
       this.name = name;
       this.path = path;
       this.type = type;
    }
   
     @Id 
     @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="CRIMINAL_DOCUMENT_SEQ")
    @SequenceGenerator(name="CRIMINAL_DOCUMENT_SEQ", sequenceName="CRIMINAL_DOCUMENT_SEQ",allocationSize=1)
    @Column(name="ID", unique=true, nullable=false, precision=20, scale=0)
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }

    
    @Column(name="CRIMINAL_ID", length=50)
    public String getCriminalId() {
        return this.criminalId;
    }
    
    public void setCriminalId(String criminalId) {
        this.criminalId = criminalId;
    }

    
    @Column(name="NAME", length=50)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="PATH", length=200)
    public String getPath() {
        return this.path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }

    
    @Column(name="TYPE", length=50)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }




}


