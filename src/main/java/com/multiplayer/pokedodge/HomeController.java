package com.multiplayer.pokedodge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @GetMapping("/hello")
    public String getMethodName() {
        return "hello";
    }
    

}
