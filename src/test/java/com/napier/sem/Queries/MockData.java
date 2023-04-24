package com.napier.sem.Queries;

import com.napier.sem.Country;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

public class MockData {
    //when we are not testing the database, we want to check against mock data just to test the methods
    //below is sample Country object data and list data to test the CountryQueries
    static Country mockCountry1 = new Country("ABC", "Country1", "Continent1", "Region1", 20, "Capital1");
    static Country mockCountry2 = new Country("DEF", "Country2", "Continent1", "Region1", 10, "Capital2");
    static Country mockCountry3 = new Country("GHI", "Country3", "Continent2", "Region2", 30, "Capital3");
    static Country mockCountry4 = new Country("JKL", "Country4", "Continent2", "Region2", 5, "Capital4");
    static Country mockCountry5 = new Country("MNO", "Country5", "Continent2", "Region2", 15, "Capital5");
    static Country actualFirstCountry = new Country("CHN","China","Asia","Eastern Asia",1277558000,"1891");
    static List<Country> allCountriesMock = Arrays.asList(mockCountry3, mockCountry1, mockCountry5, mockCountry2, mockCountry4);
    static List<Country> allContinentsMock = Arrays.asList(mockCountry3, mockCountry5, mockCountry4);
    static List<Country> allRegionsMock = Arrays.asList(mockCountry3, mockCountry5, mockCountry4);
    static List<Country> topCountriesMock = Arrays.asList(mockCountry3, mockCountry1, mockCountry5);

    public static Country getFirstCountry() {
        return actualFirstCountry;
    }

    public static List<Country> getAllCountries() {
        return allCountriesMock;
    }

    public static List<Country> getAllContinentsMock() {
        return allContinentsMock;
    }

    static public List<Country> getAllRegionsMock() {
        return allRegionsMock;
    }

    static public List<Country> getTopCountriesMock() {
        return topCountriesMock;
    }
}
