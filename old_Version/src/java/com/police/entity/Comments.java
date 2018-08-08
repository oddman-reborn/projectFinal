package com.police.entity;
// Generated 08-Aug-2017 14:17:43 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Comments generated by hbm2java
 */
@Entity
@Table(name = "COMMENTS",
         schema = "MONIR"
)
public class Comments implements java.io.Serializable {

    private BigDecimal id;
    private String userId;
    private String text;
    private String postDate;
    private String criminalId;
    private String policeStation;

    public Comments() {
    }

    public Comments(BigDecimal id) {
        this.id = id;
    }

    public Comments(BigDecimal id, String userId, String text, String postDate, String criminalId, String policeStation) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.postDate = postDate;
        this.criminalId = criminalId;
        this.policeStation = policeStation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENTS_SEQ")
    @SequenceGenerator(name = "COMMENTS_SEQ", sequenceName = "COMMENTS_SEQ", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false, precision = 20, scale = 0)
    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @Column(name = "USER_ID", length = 20)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "TEXT", length = 200)
    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "POST_DATE", length = 50)
    public String getPostDate() {
        return this.postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    @Column(name = "CRIMINAL_ID", length = 20)
    public String getCriminalId() {
        return this.criminalId;
    }

    public void setCriminalId(String criminalId) {
        this.criminalId = criminalId;
    }

    @Column(name = "POLICE_STATION", length = 100)
    public String getPoliceStation() {
        return this.policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

}