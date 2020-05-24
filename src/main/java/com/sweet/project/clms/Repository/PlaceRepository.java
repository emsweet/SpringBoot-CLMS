package com.sweet.project.clms.Repository;

import com.sweet.project.clms.Entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {
    @Query(value="SELECT * FROM place WHERE pid IN (" +
            " SELECT pid FROM place AS p WHERE p.type = ?1 AND p.campus = ?2 AND p.building = ?3 AND p.capacity > ?4 \tAND NOT EXISTS ( " +
            "SELECT * FROM reservation AS r WHERE r.place_pid = p.pid AND CONVERT(r.rdate,DATE) = CONVERT(?5 ,DATE) \t" +
            "AND (( CONVERT(r.st_time,TIME) >=CONVERT(?6,TIME) AND CONVERT(r.ed_time,TIME) <= CONVERT(?7,TIME) ) " +
            "OR ( CONVERT(r.st_time,TIME) <=CONVERT(?6,TIME) AND CONVERT(r.ed_time,TIME) >= CONVERT(?6,TIME) )" +
            "OR ( CONVERT(r.st_time,TIME) <=CONVERT(?7,TIME) AND CONVERT(r.ed_time,TIME) >= CONVERT(?7,TIME) ) " +
            ")) )",nativeQuery=true)
    List<Place> selectByoption(String type, String campus, String building, Integer capacity, String rdate,String st_time,String ed_time);

    @Query(value="SELECT distinct building from Place ",nativeQuery=true)
    List<String> findAllBuilding();

    @Query(value="SELECT distinct campus from Place ",nativeQuery=true)
    List<String> findALlCampus();
}
