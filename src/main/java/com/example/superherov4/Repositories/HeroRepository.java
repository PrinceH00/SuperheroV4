package com.example.superherov4.Repositories;

import com.example.superherov4.DTO.City;
import com.example.superherov4.DTO.SuperPower;
import com.example.superherov4.Models.Superhero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HeroRepository {
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    String SQL;
    Connection connection;


    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;


    public Connection connection() {
        try {
            connection = DriverManager.getConnection(db_url, uid, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    public List<Superhero> allHeros() {
        List<Superhero> superheroList = new ArrayList<>();
        try {
            connection();
            SQL = "select * from Superhero";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                int heroId = resultSet.getInt("HEROID");
                String heroName = resultSet.getString("HeroName");
                String privateName = resultSet.getString("PrivateName");
                String creationYear = resultSet.getString("CreationYear");
                int cityid = resultSet.getInt("CITYID");

                superheroList.add(new Superhero(heroId, heroName, privateName, creationYear, cityid));
            }
            connection().close();
            return superheroList;

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    public Superhero showHero(String name) {
        Superhero superhero = null;
        try {
            connection();
            SQL = "SELECT * FROM Superhero WHERE HeroName = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int heroId = resultSet.getInt("HEROID");
                String heroName = resultSet.getString("HeroName");
                String privateName = resultSet.getString("PrivateName");
                String creationYear = resultSet.getString("CreationYear");
                int cityid = resultSet.getInt("CITYID");

                superhero = new Superhero(heroId, heroName, privateName, creationYear, cityid);
            }
            connection().close();
            return superhero;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SuperPower> allHerosSuperpowerCount() {
        List<SuperPower> superpowerCount = new ArrayList<>();
        try {
            connection();
            SQL = "SELECT Superhero.HEROID, HeroName, PrivateName, COUNT(HEROID) AS count " +
                    "FROM Superhero JOIN SuperHeroPower USING(HEROID) GROUP BY HEROID;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                String heroName = resultSet.getString("HeroName");
                String realName = resultSet.getString("PrivateName");
                int count = resultSet.getInt("COUNT");

                superpowerCount.add(new SuperPower(heroName, realName, count));
            }
            connection().close();
            return superpowerCount;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public SuperPower superpowerCount(String name) {
        SuperPower superpowerCount = null;
        try {
            connection();
            SQL = "SELECT Superhero.HEROID, HeroName, PrivateName, COUNT(Superhero.HEROID) AS count " +
                    "FROM Superhero JOIN SuperHeroPower WHERE SuperHeroPower.HEROID = Superhero.HEROID AND HeroName =?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String heroName = resultSet.getString("HeroName");
                String realName = resultSet.getString("PrivateName");
                int count = resultSet.getInt("COUNT");

                superpowerCount = new SuperPower(heroName, realName, count);
            }
            connection().close();
            return superpowerCount;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SuperPower> showHeroWithPowers() {
        List<SuperPower> superPowers = new ArrayList<>();

        try {
            connection();
            SQL = "SELECT Superhero.HEROID, HeroName, PrivateName, Superpower FROM Superhero " +
                    "JOIN Superpower JOIN SuperHeroPower ON Superpower.POWERID = SuperHeroPower.POWERID " +
                    "AND Superhero.HEROID = SuperHeroPower.HEROID;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);

            String currentHeroName = "";
            SuperPower superPower = null;

            while (resultSet.next()) {
                String heroName = resultSet.getString("HeroName");
                String realName = resultSet.getString("PrivateName");
                String superpower = resultSet.getString("Superpower");

                if (heroName.equals(currentHeroName)) {
                    superPower.addSuperpower(superpower);
                } else {
                    superPower = new SuperPower(heroName, realName, new ArrayList<>(List.of(superpower)));
                    superPowers.add(superPower);
                    currentHeroName = heroName;
                }
            }
            connection().close();
            return superPowers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
    }
    }

        public SuperPower superHeroPower(String name) {
            SuperPower superheroSuperpower = null;
            try{
                connection();
                SQL = "SELECT Superhero.HEROID, HeroName, PrivateName, Superpower FROM Superhero " +
                        "JOIN Superpower JOIN SuperHeroPower ON Superpower.POWERID = SuperHeroPower.POWERID " +
                        "AND Superhero.HEROID = SuperHeroPower.HEROID AND HeroName = ?;";

                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setString(1, name);
                resultSet = preparedStatement.executeQuery();

                String currentHeroName = "";
                while (resultSet.next()) {
                    String heroName = resultSet.getString("HeroName");
                    String realName = resultSet.getString("PrivateName");
                    String superpower = resultSet.getString("Superpower");

                    if (heroName.equals(currentHeroName)) {
                        superheroSuperpower.addSuperpower(superpower);
                    } else {
                        superheroSuperpower = new SuperPower(heroName, realName, new ArrayList<>(List.of(superpower)));
                        currentHeroName = heroName;
                    }
                }
                connection().close();
                return superheroSuperpower;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public List<City> HeroesInCity() {
        List<City> allHeroesInCity = new ArrayList();

        try{
            connection();
            SQL = "SELECT city, HeroName FROM City JOIN Superhero WHERE Superhero.CITYID = City.CITYID GROUP BY HEROID";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);

            String currentCity = "";
            City heroCity = null;

            while(resultSet.next()) {
                String city = resultSet.getString("city");
                String heroName = resultSet.getString("HeroName ");

                if(city.equals(currentCity)){
                    heroCity.addSuperhero(heroName);
                } else {
                    heroCity = new City(city,new ArrayList<>(List.of(heroName)));
                    allHeroesInCity.add(heroCity);
                    currentCity = city;
                }
            }
            connection().close();
            return allHeroesInCity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public City herosInThisCity(String name) {
        try{
            connection();
            SQL = "SELECT city, HeroName FROM City  JOIN Superhero WHERE Superhero.CITYID = City.CITYID AND city =?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            String currentCity = "";
            City heroCity = null;

            while (resultSet.next()) {
                String city = resultSet.getString("city");
                String heroName = resultSet.getString("hero_name");

                if (city.equals(currentCity)) {
                    heroCity.addSuperhero(heroName);
                } else {
                    heroCity = new City(city, new ArrayList<>(List.of(heroName)));
                    currentCity = city;
                }
            }
            connection().close();
            return heroCity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}




