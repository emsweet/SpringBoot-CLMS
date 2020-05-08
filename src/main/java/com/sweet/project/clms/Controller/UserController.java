package com.sweet.project.clms.Controller;

import com.sweet.project.clms.Entity.User;
import com.sweet.project.clms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users/")
public class UserController {
    private UserService userService;
    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("showForm")
    public String showStudentForm(){return "add-user";}
    @GetMapping("signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }
    @GetMapping("list")
    public String users(Model model) {
        model.addAttribute("users", this.userService.findAll());
        return "show-user";
    }
    @PostMapping("add")
    public String addUser(@Valid User user, BindingResult result, Model model) {
//        如果输入结果有错，回到add-user界面（更新）
        if (result.hasErrors()) {
            return "add-user";
        }
//        保存信息到列表中，重定向到列表页面
        this.userService.saveUser(user);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long uid, Model model) {
        //更新列表时 在列表中查
        User U = userService.findById(uid);
        model.addAttribute("user",U);
        return "update-user";
    }
    @PostMapping("update/{id}")
    public String updateUser(@PathVariable("id")long uid,@Valid User user,BindingResult result,Model model)
    {
        if(result.hasErrors()){
            user.setUid(uid);
            return "update-user";
        }
        //update user
        user.setUid(uid);
        userService.saveUser(user);
        model.addAttribute("users",userService.findAll());
        return "show-user";
    }
    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id")long uid,Model model) {
        User user = userService.findById(uid);
        this.userService.deleteUser(user);
        model.addAttribute("users", this.userService.findAll());
        return "show-user";
    }
}
