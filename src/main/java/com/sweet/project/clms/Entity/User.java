package com.sweet.project.clms.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    @Column(name="name")
    private String name;
    @Column(name="phone")
    private Long phone;
    @Column(name="email")
    private String email;
    @Column(name="type")
    private String type;
    @Column(name="psw")
    private String password;

    public User(){
        super();
    }

    public User(String name, Long phone) {
        super();
        this.name = name;
        this.phone = phone;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
