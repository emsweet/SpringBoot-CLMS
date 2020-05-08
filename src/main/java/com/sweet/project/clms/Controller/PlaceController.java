package com.sweet.project.clms.Controller;

import com.sweet.project.clms.Entity.Place;
import com.sweet.project.clms.Entity.User;
import com.sweet.project.clms.Repository.PlaceRepository;
import com.sweet.project.clms.Service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/manageplace/")
public class PlaceController {
    PlaceService placeService;
    @Autowired
    private PlaceController(PlaceService placeService) {
        this.placeService=placeService;
    }
    @GetMapping("place")
    public String manageplaceAdd(Place place){
        return "show-place";
    }
    @GetMapping("list")
    public String showplace(Model model)
    {
        model.addAttribute("places",this.placeService.findAll());
        return "show-place";
    }
    @GetMapping("showaddForm")
    public String showaddPlaceForm(Place place){
        return "add-place";
    }
    @GetMapping("delete/{id}")
    public String deletePlace(@PathVariable("id")Long pid,Model model)
    {
        Place place=placeService.findByPid(pid);
        placeService.deletePlace(place);
        model.addAttribute("places",placeService.findAll());
        return "show-place";
    }
    @PostMapping("addplace")
    public String addPlace(@Valid Place place,BindingResult result, Model model)
    {
        if(result.hasErrors()){
            return "add-place";
        }
        placeService.savePlace(place);
        return "redirect:list";
    }
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long pid, Model model) {
        //更新列表时 在列表中查
        Place place= placeService.findByPid(pid);
        model.addAttribute("place",place);
        return "update-place";
    }
    @PostMapping("update/{id}")
    public String updatePlace(@PathVariable("id")long pid,@Valid Place place,BindingResult result,Model model)
    {
        place.setPid(pid);
        if(result.hasErrors()){
            return "update-user";
        }
        //update place
        placeService.savePlace(place);
        model.addAttribute("places",placeService.findAll());
        return "show-place";
    }
}
