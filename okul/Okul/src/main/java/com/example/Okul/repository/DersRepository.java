package com.example.Okul.repository;

import com.example.Okul.modal.Ders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DersRepository extends JpaRepository <Ders, Long>{
    @Query(value = "SELECT d.id,d.name,d.akts FROM  okul.ders d WHERE   d.id ;",nativeQuery = true)
    List<Ders> findAllByName(String name);

    Optional<Ders> findByName(String name);



    @Query(value = "SELECT d.id,d.name,d.akts FROM okul.ders_ogr o, okul.ders d WHERE o.ders_id= :dersId AND o.ogr_id=d.id ;",nativeQuery = true)
    List<Ders> findDerssByOgrsId(@Param("dersId") Long dersId);
}
