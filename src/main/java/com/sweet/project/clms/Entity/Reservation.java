package com.sweet.project.clms.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rid;

    @Column(name="status")
    private String status;

    @Column(name="logtime")
    private Timestamp logtime;

    @Column(name="st_time")
    private  String st_time;

    @Column(name="ed_time")
    private String ed_time;

    @Column(name="rdate")
    private String rdate;

    @ManyToOne
    private Place place;

    @ManyToOne
    private User user;

    public Reservation() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getLogtime() {
        return logtime;
    }

    public void setLogtime(Timestamp logtime) {
        this.logtime = logtime;
    }

    public String getSt_time() {
        return st_time;
    }

    public void setSt_time(String st_time) {
        this.st_time = st_time;
    }

    public String getEd_time() {
        return ed_time;
    }

    public void setEd_time(String ed_time) {
        this.ed_time = ed_time;
    }


    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "rid=" + rid +
                ", status='" + status + '\'' +
                ", logtime=" + logtime +
                ", st_time=" + st_time +
                ", ed_time=" + ed_time +
                ", rdate=" + rdate +
                ", place=" + place +
                '}';
    }
}
