package com.example.springouth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class aouthController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/protected")
    public String indexProtected() {
        return "index";
    }
}
