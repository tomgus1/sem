package com.napier.sem.Queries;

import com.napier.sem.City;
import com.napier.sem.Country;
import com.napier.sem.Language;
import com.napier.sem.Population;

import java.util.Arrays;
import java.util.List;

public class MockData {
    /**
     * when we are not testing the database, we want to check against mock data just to test the methods
     * below is sample object data and list data to test our methods
     */
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

    static City mockCapitalCity1 = new City("Capital1", "Country1", 20);
    static City mockCapitalCity2 = new City("Capital2", "Country2", 10);
    static City mockCapitalCity3 = new City("Capital3", "Country3", 30);
    static City mockCapitalCity4 = new City("Capital4", "Country4", 5);
    static City mockCapitalCity5 = new City("Capital5", "Country5", 15);
    static List<City> allCapitalCitiesMock = Arrays.asList(mockCapitalCity3, mockCapitalCity1, mockCapitalCity5, mockCapitalCity2, mockCapitalCity4);
    static List<City> capitalCitiesInContinentMock = Arrays.asList(mockCapitalCity3, mockCapitalCity5, mockCapitalCity4);
    static List<City> capitalCitiesInRegionMock = Arrays.asList(mockCapitalCity3, mockCapitalCity5, mockCapitalCity4);
    static List<City> topCapitalCitiesMock = Arrays.asList(mockCapitalCity3, mockCapitalCity1, mockCapitalCity5);

    public static List<City> getAllCapitalCities() {
        return allCapitalCitiesMock;
    }

    public static List<City> getCapitalCitiesInContinentMock() {
        return capitalCitiesInContinentMock;
    }

    static public List<City> getCapitalCitiesInRegionMock() {
        return capitalCitiesInRegionMock;
    }

    static public List<City> getTopCapitalCitiesMock() {
        return topCapitalCitiesMock;
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
    static City mockCity1 = new City(1, "City1", "ABC", "Country1", "District1", 20);
    static City mockCity2 = new City(2, "City2", "ABC", "Country1", "District2", 5);
    static City mockCity3 = new City(3, "City3", "DEF", "Country2", "District1", 2);
    static City mockCity4 = new City(4, "City4", "DEF", "Country2", "District1", 2);
    static City mockCity5 = new City(5, "City5", "DEF", "Country2", "District2", 6);
    static City mockCity6 = new City(6, "City6", "GHI", "Country3", "District1", 15);
    static City mockCity7 = new City(7, "City7", "GHI", "Country3", "District1", 10);
    static City mockCity8 = new City(8, "City8", "JKL", "Country4", "District1", 2);
    static City mockCity9 = new City(9, "City9", "JKL", "Country4", "District2", 3);
    static City mockCity10 = new City(10, "City10", "MNO", "Country5", "District1", 5);
    static City mockCity11 = new City(11, "City11", "MNO", "Country5", "District1", 5);
    static City mockCity12 = new City(12, "City12", "MNO", "Country5", "District2", 5);

    static List<City> allCitiesMock = Arrays.asList(mockCity1, mockCity2, mockCity3, mockCity4, mockCity5, mockCity6,
            mockCity7, mockCity8, mockCity9, mockCity10, mockCity11, mockCity12);
    static List<City> allCitiesInDistrictsMock = Arrays.asList(mockCity1, mockCity3, mockCity4, mockCity6,
            mockCity7, mockCity8, mockCity10, mockCity11);
    static List<City> allCityCountriesMock = Arrays.asList(mockCity1, mockCity2);
    static List<City> allCityRegionsMock = Arrays.asList(mockCity1, mockCity2, mockCity3, mockCity4, mockCity5);
    static List<City> allCityContinentsMock = Arrays.asList(mockCity1, mockCity2, mockCity3, mockCity4, mockCity5);
    static List<City> topCitiesLimitedByMock = Arrays.asList(mockCity1, mockCity2);

    public static List<City> getAllCitiesMock() {
        return allCitiesMock;
    }

    public static List<City> getAllCitiesInDistrictMock() {
        return allCitiesInDistrictsMock;
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

    public static List<City> getTopCitiesLimitedByMock() {
        return topCitiesLimitedByMock;
    }

    static Population populationCountry1 = new Population("Country 1", 1000000, 105400, 270000, 3131200, 7.9383745, 92.06163);
    static Population populationCountry2 = new Population("Country 2", 2000000, 204500, 26245483, 5591921, 31.942528, 68.05747);
    static Population populationCountry3 = new Population("Country 3", 3000000, 345355, 123298526, 89036347, 12.163672, 87.83633);
    static Population populationCountry4 = new Population("Country 4", 40000500, 5555434, 1132403, 22797597, 4.732148, 95.26785);
    static Population populationCountry5 = new Population("Country 5", 143345, 5000, 2770458, 6710542, 29.221159, 70.77884);

    static List<Population> allPopulationCountry = Arrays.asList(populationCountry1, populationCountry2, populationCountry3, populationCountry4, populationCountry5);

    public static List<Population> getAllPopulationCountry() {
        return allPopulationCountry;
    }
}
