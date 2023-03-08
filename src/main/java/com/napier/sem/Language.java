package com.napier.sem;

// Language Class
public class Language
{
    //Default constructor
    public Language() {}

    // language string
    private String language;

    // Population of people who speak the Language
    private long population;

    // Percentage of Worldwide speakers

    private float globalPercentage;

    // upadting language title
    public void setLanguage(String language) { this.language = language; }

    // Retrieves the Language Title
    public String getLanguage() { return language; }

    // Updates the population of people of the spoken language

    public void setPopulation(long population) { this.population = population; }

    // Retrieves the Population of people who speak selected  Language

    public long getPopulation() { return population; }

    // Updates the Percentage of Worldwide speakers

    public void setPercentage(float percentage) { this.globalPercentage = percentage; }

    // Retrieves the Percentage of Worldwide speakers

    public float getPercentage() { return globalPercentage; }
}