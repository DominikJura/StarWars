package co.uk.androidrecruitmenttask.data;

import java.util.List;

/**
 * Created by filipradon on 10/04/17.
 */

public class People {

    /**
     * name : Luke Skywalker
     * height : 172
     * mass : 77
     * hair_color : blond
     * skin_color : fair
     * eye_color : blue
     * birth_year : 19BBY
     * gender : male
     * homeworld : http://swapi.co/api/planets/1/
     * films : ["http://swapi.co/api/films/6/","http://swapi.co/api/films/3/","http://swapi.co/api/films/2/","http://swapi.co/api/films/1/","http://swapi.co/api/films/7/"]
     * species : ["http://swapi.co/api/species/1/"]
     * vehicles : ["http://swapi.co/api/vehicles/14/","http://swapi.co/api/vehicles/30/"]
     * starships : ["http://swapi.co/api/starships/12/","http://swapi.co/api/starships/22/"]
     * created : 2014-12-09T13:50:51.644000Z
     * edited : 2014-12-20T21:17:56.891000Z
     * url : http://swapi.co/api/people/1/
     */

    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private String created;
    private String edited;
    private String url;
    private List<String> films;
    private List<String> species;
    private List<String> vehicles;
    private List<String> starships;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public void setSkin_color(String skin_color) {
        this.skin_color = skin_color;
    }

    public String getEye_color() {
        return eye_color;
    }

    public void setEye_color(String eye_color) {
        this.eye_color = eye_color;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }
}
