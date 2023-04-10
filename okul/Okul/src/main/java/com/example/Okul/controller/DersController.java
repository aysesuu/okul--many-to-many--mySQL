package com.example.Okul.controller;

import com.example.Okul.exception.AlreadyExistsException;
import com.example.Okul.exception.NotFoundException;
import com.example.Okul.json.JsonParser;
import com.example.Okul.modal.Ders;
import com.example.Okul.modal.DersResponse;
import com.example.Okul.repository.DersRepository;
import com.example.Okul.service.DersService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api1/v2")
@NoArgsConstructor
public class DersController {
    @Autowired
    private DersService dersService;
    @Autowired
    private DersRepository dersRepository;
    @GetMapping("/dersler")
    public ResponseEntity<List<Ders>> getDers(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(dersService.getDers(name), OK);

    }
    @GetMapping("/dersler/{id}")
    public ResponseEntity<String> getDersler(@PathVariable Long id, String name) {
        try {
            if (!dersRepository.existsById(id)) {
                throw new NotFoundException("id ile ders bulunamadı = " + id);
            }
            List<Ders> derss = dersRepository.findAllByName(name);
            List<Map<String,Object>> list = new ArrayList<>();
            for(Ders ders : derss){
                list.add(new HashMap<>());
                list.get(list.size()-1).put("id",ders.getId());
                list.get(list.size()-1).put("name",ders.getName());
                list.get(list.size()-1).put("akts",ders.getAkts());
            }
            return ResponseEntity.ok(JsonParser.parse(list));
        } catch(Exception ex){
            ex.printStackTrace();
            return null;
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/createDers")
    public ResponseEntity<DersResponse> createDers(@RequestBody Ders newDers) throws AlreadyExistsException, NotFoundException {

        return new ResponseEntity<>(dersService.createDers(newDers), HttpStatus.CREATED);
    }
    @PutMapping("/updateDers/{id}")
    public ResponseEntity<DersResponse> updateDers(@PathVariable Long id, @RequestBody Ders newDers)  {
        String name ="";
        dersService.updateDers(id, newDers,name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/deleteDers/{id}")
    public ResponseEntity<Ders> deleteDers (@PathVariable Long id){
        dersService.deleteDers(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
