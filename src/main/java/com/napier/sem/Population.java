package com.napier.sem;

public class Population { // constructor
    public Population() {
    }

    // Population's name
    private String name;

    // Population County
    private long populationCountry;

    // People living in cities
    private long populationCity;

    // Updates the Population Name
    public void setName(String name) {
        this.name = name;
    }

    //gets the Population Name
    public String getName() {
        return name;
    }

    // Updates the Population of people living in Countries
    public void setCountryPopulation(long populationCountry) {
        this.populationCountry = populationCountry;
    }

    //gets the Population of people living in Countries
    public long getCountryPopulation() {
        return populationCountry;
    }

    // Updates the Population of people living in Cities
    public void setCityPopulation(long populationCity) {
        this.populationCity = populationCity;
    }

    //gets the Population of people living in Cities
    public long getCityPopulation() {
        return populationCity;
    }
}
