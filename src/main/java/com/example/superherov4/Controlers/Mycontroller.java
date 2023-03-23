package com.example.superherov4.Controlers;

import com.example.superherov4.DTO.City;
import com.example.superherov4.DTO.SuperPower;
import com.example.superherov4.Models.Superhero;
import com.example.superherov4.Repositories.HeroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "superhero")
public class Mycontroller {

    private HeroRepository repository;
    public Mycontroller(HeroRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public ResponseEntity<List<Superhero>> allHeros(){
        List<Superhero> superheroList = repository.allHeros();
        return new ResponseEntity<>(superheroList, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Superhero> showHero(@PathVariable String name){
        Superhero superhero = repository.showHero(name);
        return new ResponseEntity<>(superhero, HttpStatus.OK);
    }

    @GetMapping("/superpower/count")
    public ResponseEntity<List<SuperPower>> allHerosSuperpowerCount() {
        List<SuperPower> superpowerCount = repository.allHerosSuperpowerCount();
        return new ResponseEntity<>(superpowerCount, HttpStatus.OK);
    }

    @GetMapping("/superpower/count/{navn}")
    public ResponseEntity<SuperPower> superpowerCount(@PathVariable String name){
        SuperPower superpowerCount = repository.superpowerCount(name);
        return new ResponseEntity<>(superpowerCount, HttpStatus.OK);
    }

    @GetMapping("/superpower")
    public ResponseEntity<List<SuperPower>> superheroWithPower(){
        List<SuperPower> superPowerList = repository.showHeroWithPowers();
        return new ResponseEntity<>(superPowerList, HttpStatus.OK);
    }

    @GetMapping("/superpower/{navn}")
    public ResponseEntity<SuperPower> superHeroPower(@PathVariable String name){
        SuperPower superPower = repository.superHeroPower(name);
        return new ResponseEntity<>(superPower, HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<List<City>> heroesInCity() {
        List<City> allHeroesInCity = repository.HeroesInCity();
        return new ResponseEntity<>(allHeroesInCity,HttpStatus.OK);
    }
    @GetMapping("/city/{navn}")
    public ResponseEntity<City> herosInThisCity(@PathVariable String name){
        City city = repository.herosInThisCity(name);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
