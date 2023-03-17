package com.napier.sem;

public class City
{
    // Default constructor
    public City() {}

    // ID
    private int id;

    // City's Name
    private String name;

    // City's Country Code
    private String countryCode;

    // City's District
    private String district;

    // City's Population
    private int population;

    // Updates the City ID
    public void setId(int id) {
        this.id = id;
    }

    //gets the City ID
    public int getId() {
        return id;
    }

    // refreshed city's Name
    public void setName(String name) {
        this.name = name;
    }

    //gets city's Name
    public String getName() {
        return name;
    }

    // Updates the Country Code
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    // gets the Country Code of the City
    public String getCountryCode() {
        return countryCode;
    }

    // Updates the City District
    public void setDistrict(String district) {
        this.district = district;
    }

    //gets the City District
    public String getDistrict() {
        return district;
    }

    // Updates the City Population
    public void setPopulation(int population) {
        this.population = population;
    }

    // gets the City Population
    public int getPopulation() {
        return population;
    }
}
