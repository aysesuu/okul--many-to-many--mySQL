package com.example.Okul.service;

import com.example.Okul.exception.AlreadyExistsException;
import com.example.Okul.exception.NotFoundException;
import com.example.Okul.modal.Ders;
import com.example.Okul.modal.DersResponse;
import com.example.Okul.repository.DersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DersService {
    private String name;

    @Autowired
    private DersRepository dersRepository;


    public DersResponse createDers(Ders newDers) throws AlreadyExistsException, NotFoundException {
        Optional<Ders> dersByName=dersRepository.findByName(newDers.getName());
        if (dersByName.isPresent()){
            throw new AlreadyExistsException(newDers.getName()+"adıyla ders var");

        }
        return new DersResponse(dersRepository.save(newDers),"kaydedildi");
    }
    public List<Ders> getDersler(String name) {
        if (name == null){
            return dersRepository.findAll();

        }else {
            return dersRepository.findAllByName(name);
        }
    }

    public DersResponse updateDers(Long id, Ders newDers , String name)  {
        Optional<Ders> oldDers=dersRepository.findById(id);

        Ders sonuc=oldDers.get();
    sonuc.setName(newDers.getName());
    sonuc.setAkts(newDers.getAkts());
    return  new DersResponse(dersRepository.save(sonuc),"ders güncellendi");

    }

    public Optional<Ders> getDersById(Long id)  {
        return dersRepository.findById(id);
    }
    public void  deleteDers (Long id){
        dersRepository.deleteById(id);
    }

    public List<Ders> getDers(String name) {
        if (name == null) {
            return dersRepository.findAll();
        } else {
            return dersRepository.findAllByName(name);
        }
    }
}
