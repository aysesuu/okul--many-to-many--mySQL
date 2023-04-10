package com.example.Okul.service;

import com.example.Okul.exception.AlreadyExistsException;
import com.example.Okul.modal.Ogr;
import com.example.Okul.modal.OgrResponse;
import com.example.Okul.repository.OgrRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor

@Service
public class OgrService {

    private String name;

    public OgrService(String name) {
        this.name = name;
    }

    public OgrService() {
    }

    @Autowired
    private OgrRepository ogrRepository;

    public List<Ogr> getOgrenciler(Long id){
       if (id== null){
           return ogrRepository.findAll();
       }else {
           return ogrRepository.findAllById(id);
       }
    }

    public OgrResponse createOgr(Ogr newOgr) throws AlreadyExistsException {
        Optional<Ogr> ogrByName=ogrRepository.findByName(newOgr.getName());
        if (ogrByName.isPresent()){
            throw new AlreadyExistsException(newOgr.getName()+"adıyla ders var");

        }
        return new OgrResponse(ogrRepository.save(newOgr),"kaydedildi");
    }

    public OgrResponse updateOgr(Long id, Ogr newOgr, String name)  {
        Optional<Ogr>oldOgr=ogrRepository.findById(id);
        Ogr sonuc=oldOgr.get();
        sonuc.setName(newOgr.getName());
        sonuc.setStudentNum(newOgr.getStudentNum());
        return  new OgrResponse(ogrRepository.save(sonuc),"güncellendi.");

    }

    public Optional<Ogr> getOgrById(Long id)  {
        System.out.println("check 2");
        return ogrRepository.findById(id);

    }

    public void deleteOgr(Long id) {
        ogrRepository.deleteById(id);
    }


    public Optional<Ogr> getOgr(Long id) {
        return ogrRepository.findById(id);
    }
}
