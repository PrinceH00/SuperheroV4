package com.example.superherov4.DTO;

import java.util.List;

public class SuperPower {
    private String heroName;
    private String privateName;
    private List<String> superpowers;
    private int count;

    public SuperPower(String heroName, String privateName, List<String> superpowers) {
        this.heroName = heroName;
        this.privateName = privateName;
        this.superpowers = superpowers;
    }

    public SuperPower(String heroName, String privateName, int count) {
        this.heroName = heroName;
        this.privateName = privateName;
        this.count = count;
    }

    public String heroName() {
        return heroName;
    }

    public String privateName() {
        return privateName;
    }

    public List<String> superpowers() {
        return superpowers;
    }

    public int count() {
        return count;
    }

    public void addSuperpower(String name) {
        superpowers.add(name);
    }

    }


