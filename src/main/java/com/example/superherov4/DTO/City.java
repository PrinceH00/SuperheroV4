package com.example.superherov4.DTO;

import java.util.List;

public class City {
    private String city;
    private List<String> heroList;
    public City(String city, List <String> heroList) {
        this.city = city;
        this.heroList = heroList;
    }

    public String city() {
        return city;
    }

    public List<String> heroList() {
        return heroList;
    }

    public void addSuperhero(String name){
        heroList.add(name);
    }
}

