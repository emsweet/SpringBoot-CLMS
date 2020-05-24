package com.sweet.project.clms.Service;

import com.sweet.project.clms.Entity.Place;
import com.sweet.project.clms.Entity.Reservation;
import com.sweet.project.clms.Entity.User;
import com.sweet.project.clms.Repository.PlaceRepository;
import com.sweet.project.clms.Repository.ReserveRepository;
import com.sweet.project.clms.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Service
public class ReserveService {
    private ReserveRepository reserveRepository;
    private PlaceRepository placeRepository;
    private UserRepository userRepository;
    @Autowired
    private ReserveService(ReserveRepository reserveRepository, PlaceRepository placeRepository,UserRepository userRepository){
        this.placeRepository=placeRepository;
        this.userRepository=userRepository;
        this.reserveRepository=reserveRepository;
    }
    public List<Place> selectByOption(String type, String campus, String building, Integer capacity, String rdate,String st_time,String ed_time)
    {
        return placeRepository.selectByoption(type,campus,building,capacity, rdate,st_time,ed_time);
    }

    public List<Reservation> selectByUser(Principal principal)
    {
       User user=userRepository.findByName(principal.getName()).orElseThrow(()->new IllegalArgumentException("Invalid User Name"+principal.getName()));
       return reserveRepository.selectByUser(user.getUid());
    }
    public List<Reservation>selectByStatus(String status)
    {
        return reserveRepository.selectByStatus(status);
    }
    public void addReservation(Reservation reservation)
    {
        reservation.setStatus("pending");
        long pid=reservation.getPlace().getPid();
        Place place=placeRepository.findById(pid).orElseThrow(()->new IllegalArgumentException("Invalid place_id:"+pid));
        reservation.setPlace(place);
        reservation.setLogtime(new Timestamp(System.currentTimeMillis()));
        System.out.println(reservation);
        reserveRepository.save(reservation);
    }
    public List<Reservation> getRequestList()
    {
        return reserveRepository.selectByStatus("pending");
    }

    public void updateReserveStatus(long rid,String status)
    {
        Reservation reservation=reserveRepository.findById(rid).orElseThrow(()->new IllegalArgumentException("Invalid rid:"+rid));
        reservation.setStatus(status);
        reserveRepository.save(reservation);
    }
}
