package com.sweet.project.clms.Service;

import com.sweet.project.clms.Entity.Place;
import com.sweet.project.clms.Entity.User;
import com.sweet.project.clms.Repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;
    @Autowired
    private PlaceService(PlaceRepository placeRepository){this.placeRepository=placeRepository;}

    public List<Place> findAll()
    {
        return placeRepository.findAll();
    }
    public Place findByPid(long place_id)
    {
        Place place=placeRepository.findById(place_id).orElseThrow(()->new IllegalArgumentException("Invalid Place"+place_id));
        return place;
    }
    public void deletePlace(Place place){
        placeRepository.delete(place);
    }
    public void savePlace(Place place)
    {
        placeRepository.save(place);
    }


    public List<String> findAllBuilding(){return placeRepository.findAllBuilding();}
    public List<String> findAllCampus(){return placeRepository.findALlCampus();}
}
