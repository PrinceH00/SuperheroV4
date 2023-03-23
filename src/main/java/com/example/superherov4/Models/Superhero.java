package com.example.superherov4.Models;

public class Superhero {

    private int HeroId;
    private String HeroName;
    private String PrivateName;
    private int CreationYear;
    private int CityID;

    public Superhero(String heroName, String privateName, int creationYear) {
        HeroName = heroName;
        PrivateName = privateName;
        CreationYear = creationYear;
    }

    public Superhero(int heroId, String heroName, String privateName, int creationYear, int cityID) {
        HeroId = heroId;
        HeroName = heroName;
        PrivateName = privateName;
        CreationYear = creationYear;
        CityID = cityID;
    }

    public int HeroId() {
        return HeroId;
    }

    public String HeroName() {
        return HeroName;
    }

    public String PrivateName() {
        return PrivateName;
    }

    public int CreationYear() {
        return CreationYear;
    }

    public int CityID() {
        return CityID;
    }
}
