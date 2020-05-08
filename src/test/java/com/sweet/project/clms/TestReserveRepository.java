package com.sweet.project.clms;


import com.sweet.project.clms.Entity.Place;
import com.sweet.project.clms.Entity.Reservation;
import com.sweet.project.clms.Repository.PlaceRepository;
import com.sweet.project.clms.Repository.ReserveRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Time;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestReserveRepository {
    @Autowired
    ReserveRepository reserveRepository;
    @Autowired
    PlaceRepository placeRepository;

    @PersistenceContext
    private EntityManager entityManager;
    @Test
    public void testReserveDao1()
    {
        System.out.printf("%s", reserveRepository.selectbyid((long) 1));
    }
    @Test
    public void testReserveDao2()
    {
        List<Reservation> reservationList= reserveRepository.selectbypid((long)55);
        Iterator it=reservationList.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    @Test
    public void testPlaceDAO(){
        List<String> buildingList=placeRepository.findALlCampus();
        Iterator it=buildingList.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    public void TestPlace()
    {
        Place place;
        place = placeRepository.findById((long)57).orElseThrow(() -> new IllegalArgumentException("Invalid Place id" + 53));
        System.out.println(place);
    }


    @Test
    public void TestReserveDao_3()
    {
        List<Place> placeList=placeRepository.selectByoption("L","江浦校区","机械A楼",0,"2020-05-06","16:00","17:00");
        Iterator it=placeList.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    @Test
    public void testCase()
    {
        Reservation reservation=new Reservation();
        reservation.setStatus("123");
        if(reservation.getStatus().equals("123"))
        System.out.println(reservation.getStatus());
        else
            System.out.println("222");
    }

}
