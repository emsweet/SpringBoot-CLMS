package com.sweet.project.clms.Controller;

import com.sweet.project.clms.Entity.Place;
import com.sweet.project.clms.Entity.Reservation;
import com.sweet.project.clms.Entity.User;
import com.sweet.project.clms.Service.PlaceService;
import com.sweet.project.clms.Service.ReserveService;
import com.sweet.project.clms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/reserve/")
public class ReserveController {
    ReserveService reserveService;
    PlaceService placeService;
    UserService userService;

    Reservation reservation;
    @Autowired
    private ReserveController(ReserveService reserveService, PlaceService placeService,UserService userService) {
        this.placeService = placeService;
        this.reserveService = reserveService;
        this.userService=userService;
    }

    @GetMapping("list")
    public String showsearchForm(Reservation reservation, Model model) {
        setConstantModel(model);
        return "search-reserve";
    }
    @GetMapping("makeReserve/{id}")
    public String showReserveForm(@PathVariable("id")Long pid,Principal principal,Model model)
    {
        System.out.println(reservation);
        System.out.println(principal.getName());
        System.out.println(pid);
        User user= userService.findByName(principal.getName());
        Place place=placeService.findByPid(pid);
        reservation.setUser(user);
        reservation.setPlace(place);
        System.out.println(reservation);
        model.addAttribute("reservation",reservation);
        return "reserve-form";
    }
    @GetMapping("requestlist")
    public String showRequests(Reservation reservation,Model model){
        model.addAttribute("reservations",reserveService.getRequestList());
        return "request-list";
    }
    @GetMapping("accept/{id}")
    public String acceptRequest(@PathVariable("id")Long rid,Model model)
    {
        reserveService.updateReserveStatus(rid,"active");
        return "redirect:/reserve/requestlist";
    }
    @GetMapping("reject/{id}")
    public String rejectRequest(@PathVariable("id")Long rid,Model model)
    {
        reserveService.updateReserveStatus(rid,"denied");
        return "redirect:/reserve/requestlist";
    }
    @PostMapping("search")
    public String searchPlace(@ModelAttribute("reservation") Reservation reservation, Model model) {

        if (reservation.getStatus() == null) {

        } else {
            List<Place> placeList = reserveService.selectByOption(reservation.getPlace().getType(),
                    reservation.getPlace().getCampus(), reservation.getPlace().getBuilding(), reservation.getPlace().getCapacity(),
                    reservation.getRdate(), reservation.getSt_time(), reservation.getEd_time());
            model.addAttribute("places", placeList);
            this.reservation=reservation;
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
    @PostMapping("addreserve")
    public String addReservation(@Valid Reservation reservation, BindingResult result, Model model,Principal principal)
    {
        reservation.setUser(userService.findByName(principal.getName()));

        reserveService.addReservation(reservation);
        return "redirect:history";
    }
    @GetMapping("history")
    public String showReserveHistory(Principal principal,Reservation reservation,Model model)
    {
        model.addAttribute("myreserve",reserveService.selectByUser(principal));
        return "reserve-list";
    }
}
