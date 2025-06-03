package com.alvirg.jobportal.controller;

import com.alvirg.jobportal.entity.Users;
import com.alvirg.jobportal.entity.UsersType;
import com.alvirg.jobportal.services.UserService;
import com.alvirg.jobportal.services.UsersTypeServie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final UsersTypeServie usersTypeServie;
    private final UserService userService;

    @Autowired
    public UsersController(UsersTypeServie usersTypeServie, UserService userService) {
        this.usersTypeServie = usersTypeServie;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model){
        List<UsersType> usersTypes = usersTypeServie.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users, Model model){
        Optional<Users> optionalUsers = userService.getUserByEmail(users.getEmail());
        if(optionalUsers.isPresent()){
            model.addAttribute("error", "Email already registered, login or register with other Email" );
            List<UsersType> usersTypes = usersTypeServie.getAll();
            model.addAttribute("getAllTypes", usersTypes);
            model.addAttribute("user", new Users());
            return "register";
        }
        userService.addNew(users);
        return "dashboard";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null ){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }


}
