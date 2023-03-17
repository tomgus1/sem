package com.napier.sem;


public class Country
{
    // constructor
    public Country() {}

    // Country's Code
    private String code;

    // Country's Name
    private String name;

    //Country's Continent
    private String continent;

    // Country's Region
    private String region;

    // Country Population
    private int population;
    // Country's Capital

    private String capital;

    // Updates the Country Code
    public void setCode(String code) {
        this.code = code;
    }

    // gets the Country Code
    public String getCode() {
        return code;
    }

    // gets the Country Name
    public void setName(String name) {
        this.name = name;
    }

    //gets the Country Name
    public String getName() {
        return name;
    }

    // Updates the Country Continent
    public void setContinent(String continent) {
        this.continent = continent;
    }

    // gets the Country Continent
    public String getContinent() {
        return continent;
    }

    //Updates the Country Region
    public void setRegion(String region) {
        this.region = region;
    }

    //gets the Country RegionÏ
    public String getRegion() {
        return region;
    }

    // Updates the Country Population
    public void setPopulation(int population) {
        this.population = population;
    }

    //gets the Country Population
    public int getPopulation() {
        return population;
    }

    // Updates the Country Capital
    public void setCapital(String capital) {
        this.capital = capital;
    }

    // gets the Country Capital
    public String getCapital() {
        return capital;
    }
}
