package com.example.superherov4.Models;

public class Superhero {
    String HeroHero;
    String PrivateName;
    int CreationYear;

    public Superhero(String heroHero, String privateName, int creationYear) {
        HeroHero = heroHero;
        PrivateName = privateName;
        CreationYear = creationYear;
    }

    public String HeroHero() {
        return HeroHero;
    }

    public String PrivateName() {
        return PrivateName;
    }

    public int CreationYear() {
        return CreationYear;
    }
}
