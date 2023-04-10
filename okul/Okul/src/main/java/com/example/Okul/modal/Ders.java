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
@Table(name = "ders")
public class Ders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "akts")
    private Integer akts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JoinTable(name = "ders_ogr",
               joinColumns = @JoinColumn(name = "ders_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "ogr_id",referencedColumnName = "id"))
    private List<Ogr> ogrs=new ArrayList<>();

    public List<Ogr> getOgrs() {
        return ogrs;
    }

    public void setOgrs(List<Ogr> ogrs) {
        this.ogrs = ogrs;
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

    public Integer getAkts() {
        return akts;
    }

    public void setAkts(Integer akts) {
        this.akts = akts;
    }
    public Ders(){
    }
    public Ders(String name,Integer akts) {
        this.name = name;
        this.akts = akts;
    }


    public void addOgr(Ogr ogr) {
        this.ogrs.add(ogr);
        ogr.getDerss().add(this);
    }

    public void removeOgr(Long ogrId) {
        Ogr ogr =this.ogrs.stream().filter(o ->o.getId()== ogrId).findFirst().orElse(null);
        if (ogr != null){
            this.ogrs.remove(ogr);
            ogr.getDerss().remove(this);
        }
    }
}
