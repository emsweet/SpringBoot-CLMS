package com.sweet.project.clms.Controller;

import com.sweet.project.clms.Entity.Place;
import com.sweet.project.clms.Entity.Reservation;
import com.sweet.project.clms.Service.PlaceService;
import com.sweet.project.clms.Service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/reserve/")
public class ReserveController {
    PlaceService placeService;
    ReserveService reserveService;
    @Autowired
    private ReserveController(ReserveService reserveService, PlaceService placeService) {
        this.placeService = placeService;
        this.reserveService = reserveService;
    }

    @GetMapping("list")
    public String showsearchForm(Reservation reservation, Model model) {
        setConstantModel(model);
        return "search-reserve";
    }
    @GetMapping("makeReserve")
    public String showReserveForm(Principal principal)
    {
        System.out.println(principal.getName());
        return "redirect:list";
    }

    //显示用户预定记录 pengding/closed/active
    public String showReserveHistory(Principal principal)
    {
        return "reserve-list";
    }

    @PostMapping("search")
    public String searchPlace(@ModelAttribute("reservation") Reservation reservation, Model model) {

        if (reservation.getStatus() == null) {

        } else {
            List<Place> placeList = reserveService.selectByOption(reservation.getPlace().getType(),
                    reservation.getPlace().getCampus(), reservation.getPlace().getBuilding(), reservation.getPlace().getCapacity(),
                    reservation.getRdate(), reservation.getSt_time(), reservation.getEd_time());
            model.addAttribute("places", placeList);
        }
        setConstantModel(model);
        return "search-reserve";
    }

    public void setConstantModel(Model model) {
        List<String> campuslist =placeService.findAllCampus();
        model.addAttribute("campuses", campuslist);
        List<String> buildinglist = placeService.findAllBuilding();
        model.addAttribute("buildings", buildinglist);
    }
    public void UpdateReserveStatus(long pid,String status) {
        reserveService.updateReserveStatus(pid, status);
    }
}
