package com.sweet.project.clms.Entity;

import javax.persistence.*;

@Entity
@Table(name="place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;
    @Column(name="name")
    private String name;
    @Column(name="capacity")
    private Integer capacity;
    @Column(name="campus")
    private String campus;
    @Column(name="building")
    private String building;
    @Column(name="type")
    private String type;

    public Place() {
        super();
    }

    public Place(Long pid, String name, Integer capacity, String campus, String building, String type) {
        super();
        this.pid = pid;
        this.name = name;
        this.capacity = capacity;
        this.campus = campus;
        this.building = building;
        this.type = type;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Place{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", campus='" + campus + '\'' +
                ", building='" + building + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
