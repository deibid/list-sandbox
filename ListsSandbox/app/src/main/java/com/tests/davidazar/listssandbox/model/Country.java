package com.tests.davidazar.listssandbox.model;

/**
 * Created by David on 24/02/17.
 */

public class Country {

    private String name;
    private String population;
    private String imageUrl;
    private String description;


    public Country(String name, String population, String imageUrl, String description) {
        this.name = name;
        this.population = population;
        this.imageUrl = imageUrl;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
