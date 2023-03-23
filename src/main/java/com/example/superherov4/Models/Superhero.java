package com.example.superherov4.Models;

public class Superhero {
    String HeroHero;
    String PrivateName;
    int CreationYear;
    private int HeroId;

    public Superhero(String heroHero, String privateName, int creationYear,int heroId) {
        HeroHero = heroHero;
        PrivateName = privateName;
        CreationYear = creationYear;
        HeroId = heroId;
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

    public int HeroId() {
        return HeroId;
    }

    public Superhero setHeroHero(String heroHero) {
        HeroHero = heroHero;
        return this;
    }

    public Superhero setPrivateName(String privateName) {
        PrivateName = privateName;
        return this;
    }

    public Superhero setCreationYear(int creationYear) {
        CreationYear = creationYear;
        return this;
    }

    public Superhero setHeroId(int heroId) {
        HeroId = heroId;
        return this;
    }
}
