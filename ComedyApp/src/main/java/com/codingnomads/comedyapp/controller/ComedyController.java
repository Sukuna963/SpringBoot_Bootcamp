package com.codingnomads.comedyapp.controller;


import com.codingnomads.comedyapp.service.ComedyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComedyController {

    @Autowired
    private ComedyService comedyService;

    @GetMapping("/comedy")
    public String comedy(Model model){
        String joke = comedyService.getRandomJoke();

        model.addAttribute("joke", joke);

        return "comedy";
    }
}
