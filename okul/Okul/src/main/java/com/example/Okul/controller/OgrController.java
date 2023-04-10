package com.example.Okul.controller;

import com.example.Okul.exception.AlreadyExistsException;
import com.example.Okul.exception.NotFoundException;
import com.example.Okul.json.JsonParser;
import com.example.Okul.modal.Ders;
import com.example.Okul.modal.Ogr;
import com.example.Okul.modal.OgrResponse;
import com.example.Okul.repository.DersRepository;
import com.example.Okul.repository.OgrRepository;
import com.example.Okul.service.OgrService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api1/v2")
public class OgrController {
    @Autowired
    private OgrService ogrService;
    @Autowired
    private OgrRepository ogrRepository;

    @Autowired
    private DersRepository dersRepository;

    @GetMapping("/ogrler/{id}")
    public ResponseEntity<String> getOgrler(@PathVariable Long id,String name) {
        try {
            if (!ogrRepository.existsById(id)) {
                throw new NotFoundException("id ile ogr bulunamadı = " + id);
            }
            List<Ogr> ogrs = ogrRepository.findAllByName(name);
            List<Map<String,Object>> list = new ArrayList<>();
            for(Ogr ogr : ogrs){
                list.add(new HashMap<>());
                list.get(list.size()-1).put("id",ogr.getId());
                list.get(list.size()-1).put("name",ogr.getName());
                list.get(list.size()-1).put("student_Num",ogr.getStudentNum());
            }
            return ResponseEntity.ok(JsonParser.parse(list));
        } catch(Exception ex){
            ex.printStackTrace();
            return null;
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/derss/{dersId}/ogr")
    public ResponseEntity<String> getAllOgrsByDerssId(@PathVariable(value = "dersId") Long dersId) throws NotFoundException{
        try {
            if (!dersRepository.existsById(dersId)) {
                throw new NotFoundException("id ile ders bulunamadı" + dersId);
            }
            List<Ogr> ogrs = ogrRepository.findOgrsByDerssId(dersId);
            List<Map<String,Object>> list = new ArrayList<>();
            for(Ogr ogr : ogrs){
                list.add(new HashMap<>());
                list.get(list.size()-1).put("id",ogr.getId());
                list.get(list.size()-1).put("name",ogr.getName());
                list.get(list.size()-1).put("student_Num",ogr.getStudentNum());
            }
            return ResponseEntity.ok(JsonParser.parse(list));
        } catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    @GetMapping("/ogrs/{ogrId}/derss")
    public ResponseEntity<String> getAllDerssByOgrsId(@PathVariable(value = "ogrId") Long ogrId) throws NotFoundException {
        try {
            if (!ogrRepository.existsById(ogrId)) {
                throw new NotFoundException("id ile ogr bulunamadı = " + ogrId);
            }
            List<Ders> derss = dersRepository.findDerssByOgrsId(ogrId);
            List<Map<String,Object>> list=new ArrayList<>();
            for (Ders ders : derss){
                list.add(new HashMap<>());
                list.get(list.size()-1).put("id",ders.getId());
                list.get(list.size()-1).put("name",ders.getName());
                list.get(list.size()-1).put("akts",ders.getAkts());
            }
            return  ResponseEntity.ok(JsonParser.parse(list));
        } catch(Exception ex){
                ex.printStackTrace();
                return null;
        }
    }
    @PostMapping("/derss/{dersId}/ogrs")
    public ResponseEntity<Ogr> addOgr(@PathVariable(value = "dersId") Long dersId, @RequestBody Ogr ogrRequest)  throws NotFoundException{
        Ogr ogr = dersRepository.findById(dersId).map(ders -> {
            long ogrId = ogrRequest.getId();

            // ogr var
            if (ogrId != 0L) {
                Ogr _ogr;
                try {
                    _ogr = ogrRepository.findById(ogrId)
                            .orElseThrow(() -> new NotFoundException("id ile ogr bulunamadı = " + ogrId));
                } catch (NotFoundException e) {
                    throw new RuntimeException(e);
                }
                ders.addOgr(_ogr);
                dersRepository.save(ders);
                return _ogr;
            }

            ders.addOgr(ogrRequest);
            return ogrRepository.save(ogrRequest);
        }).orElseThrow(() -> new NotFoundException("id ile ders bulunamadı = " + dersId));

        return new ResponseEntity<>(ogr, HttpStatus.CREATED);
    }

    @PostMapping("/createOgr")
    public ResponseEntity<OgrResponse> createOgr (@RequestBody Ogr newOgr) throws AlreadyExistsException {
        return  new ResponseEntity<>(ogrService.createOgr(newOgr), HttpStatus.CREATED);

    }
    @PutMapping("/updateOgr/{id}")
    public ResponseEntity<OgrResponse> updateOgr(@PathVariable Long id, @RequestBody Ogr newOgr) throws NotFoundException {
        String name ="";
        ogrService.updateOgr(id, newOgr,name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/derss/{dersId}/ogrs/{ogrId}")
    public ResponseEntity<HttpStatus> deleteOgrFromDers(@PathVariable(value = "dersId") Long dersId, @PathVariable(value = "ogrId") Long ogrId) throws NotFoundException{
        Ders ders = dersRepository.findById(dersId)
                .orElseThrow(() -> new NotFoundException("id ile ders bulunamadı = " + dersId));

        ders.removeOgr(ogrId);
        dersRepository.save(ders);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/deleteOgr/{id}")
    public ResponseEntity<Ogr> deleteOgr (@PathVariable Long id){
        ogrService.deleteOgr(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
