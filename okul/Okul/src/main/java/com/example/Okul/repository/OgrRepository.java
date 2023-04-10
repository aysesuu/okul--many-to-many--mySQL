package com.example.Okul.repository;

import com.example.Okul.modal.Ogr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OgrRepository extends JpaRepository<Ogr,Long> {
    @Query(value = "SELECT o.id,o.name,o.student_Num FROM  okul.ogrenci o WHERE   o.id ;",nativeQuery = true)

    List<Ogr> findAllByName(String name);
    Optional<Ogr> findByName(String name);

    @Query(value = "SELECT o.id,o.name,o.student_Num FROM okul.ders_ogr d, okul.ogrenci o WHERE d.ders_id= :dersId AND d.ogr_id=o.id ;",nativeQuery = true)
    List<Ogr> findOgrsByDerssId(@Param("dersId") Long dersId);

    List<Ogr> findAllById(Long id);
}
