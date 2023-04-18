package com.napier.sem.Queries;

import com.napier.sem.City;
import com.napier.sem.Country;
import com.napier.sem.Language;

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
    static List<Country> allCountriesMock = Arrays.asList(mockCountry3, mockCountry1, mockCountry5, mockCountry2, mockCountry4);
    static List<Country> allContinentsMock = Arrays.asList(mockCountry3, mockCountry5, mockCountry4);
    static List<Country> allRegionsMock = Arrays.asList(mockCountry3, mockCountry5, mockCountry4);
    static List<Country> topCountriesMock = Arrays.asList(mockCountry3, mockCountry1, mockCountry5);

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

    static Language mockLanguage1 = new Language("Chinese", 1191843539, 19.61F);
    static Language mockLanguage2 = new Language("Hindi", 405633070, 6.67F);
    static Language mockLanguage3 = new Language("Spanish", 355029462, 5.84F);
    static Language mockLanguage4 = new Language("English", 347077867, 5.71F);
    static Language mockLanguage5 = new Language("Arabic", 233839238, 3.85F);

    public static List<Language> getAllLanguages() {
        return languagesMock;
    }

    static public List<Language> languagesMock = Arrays.asList(mockLanguage1, mockLanguage2, mockLanguage3, mockLanguage4, mockLanguage5);

    //below is sample City object data and list data to test the PopulationSubsetCitiesQueryTest
    static City mockCity1 = new City(1, "City1", "ABC", "District1", 15);
    static City mockCity2 = new City(2, "City2", "ABC", "District2", 5);
    static City mockCity3 = new City(3, "City3", "DEF", "District1", 2);
    static City mockCity4 = new City(4, "City4", "DEF", "District1", 2);
    static City mockCity5 = new City(5, "City5", "DEF", "District2", 6);
    static City mockCity6 = new City(6, "City6", "GHI", "District1", 15);
    static City mockCity7 = new City(7, "City7", "GHI", "District1", 15);
    static City mockCity8 = new City(8, "City8", "JKL", "District1", 2);
    static City mockCity9 = new City(9, "City9", "JKL", "District2", 3);
    static City mockCity10 = new City(10, "City10", "MNO", "District1", 5);
    static City mockCity11 = new City(11, "City11", "MNO", "District1", 5);
    static City mockCity12 = new City(12, "City12", "MNO", "District2", 5);

    static List<City> allCitiesMock = Arrays.asList(mockCity1,mockCity2,mockCity3,mockCity4,mockCity5,mockCity6,
            mockCity7,mockCity8,mockCity9,mockCity10,mockCity11,mockCity12);
    static List<City> allDistrictsMock = Arrays.asList(mockCity6,mockCity7);
    static List<City> allCityCountriesMock = Arrays.asList(mockCity1,mockCity2);
    static List<City> allCityRegionsMock = Arrays.asList(mockCity1,mockCity2,mockCity3,mockCity4,mockCity5);
    static List<City> allCityContinentsMock = Arrays.asList(mockCity6,mockCity7,mockCity8,mockCity9,mockCity10,
            mockCity10,mockCity11,mockCity12);
    static List<City> topCitiesMock = Arrays.asList(mockCity1,mockCity7,mockCity8,mockCity5);

    public static List<City> getAllCitiesMock() {
        return allCitiesMock;
    }
    public static List<City> getAllCitiesInDistrictMock() {
        return allDistrictsMock;
    }
    public static List<City> getAllCitiesInCountriesMock() {
        return allCityCountriesMock;
    }
    public static List<City> getAllCitiesInRegionsMock() {
        return allCityRegionsMock;
    }
    public static List<City> getAllCitiesInContinentsMock() {
        return allCityContinentsMock;
    }
    public static List<City> getTopCitiesMock() {
        return topCitiesMock;
    }
}
