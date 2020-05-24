package com.sweet.project.clms.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("admin")
    public String admin() {
        return "admin-home";
    }
    @GetMapping("user")
    public String user() {
        return "user-home";
    }
}