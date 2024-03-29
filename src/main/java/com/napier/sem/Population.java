package com.napier.sem;
/**
 * Populaton class
 */
public class Population {
    /**
     * Default constructor
     */
    public Population() {
    }

    public Population(String name, long populationCountry, long populationCity, long livingInCities, long livingOutside, double livingInCitiesPercentage, double livingOutsidePercentage) {
        this.name = name;
        this.populationCountry = populationCountry;
        this.populationCity = populationCity;
        this.livingInCities = livingInCities;
        this.livingOutside = livingOutside;
        this.livingInCitiesPercentage = livingInCitiesPercentage;
        this.livingOutsidePercentage = livingOutsidePercentage;
    }
    /**
     * linvingInCities value
     */
    private long livingInCities;
    /**
     *livingOutSideCitiesValue
     */
    private long livingOutside;
    /**
     *livingInCitiesPercentage
     */
    private double livingInCitiesPercentage;
    /**
     *livingOutsideCitiesPercentage
     */
    private double livingOutsidePercentage;

    /**
     * Population's name
     */
    private String name;

    /**
     * Population County
     */
    private long populationCountry;

    /**
     * People living in cities
     */
    private long populationCity;

    /**
     * Updates the Population Name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the Population Name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the Population of people living in Countries
     */
    public void setCountryPopulation(long populationCountry) {
        this.populationCountry = populationCountry;
    }

    /**
     * gets the Population of people living in Countries
     */
    public long getCountryPopulation() {
        return populationCountry;
    }

    /**
     * Updates the Population of people living in Cities
     */
    public void setCityPopulation(long populationCity) {
        this.populationCity = populationCity;
    }

    /**
     * gets the Population of people living in Cities
     */
    public long getCityPopulation() {
        return populationCity;
    }
}
