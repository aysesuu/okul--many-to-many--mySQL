package com.example.Okul.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
//@Data
@Entity
@Table(name = "ogrenci")
public class Ogr  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "student_Num")
    private Integer studentNum;

    @ManyToMany(mappedBy = "ogrs")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<Ders> derss= new ArrayList<>();

    public List<Ders> getDerss() {
        return derss;
    }

    public void setDerss(List<Ders> derss) {
        this.derss = derss;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }
    public Ogr(){}
    public Ogr(String name,Integer studentNum) {
        this.name = name;
        this.studentNum = studentNum;
    }



}
