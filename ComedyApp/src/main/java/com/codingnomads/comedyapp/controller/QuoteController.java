package com.codingnomads.comedyapp.controller;

import com.codingnomads.comedyapp.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/quote")
    public String quotes(Model model) {
        String quote = quoteService.getRandomQuotes();
        model.addAttribute("quote", quote);
        return "quotes";
    }
}
