package com.example.superherov4.Controlers;

import com.example.superherov4.Repositories.HeroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "superhero")
public class Mycontroller {

    private HeroRepository repository;

    public Mycontroller(HeroRepository repository) {
        this.repository = repository;
    }


}
