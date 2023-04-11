package com.napier.sem.Queries;

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
    static Language mockLanguage2 = new Language("Hindi",405633070,6.67F);
    static Language mockLanguage3 = new Language("Spanish",355029462,5.84F);
    static Language mockLanguage4 = new Language("English",347077867,5.71F);
    static Language mockLanguage5 = new Language("Arabic",233839238,3.85F);

    public static List<Language> getAllLanguages() {
        return languagesMock;
    }
    static public List<Language> languagesMock = Arrays.asList(mockLanguage1, mockLanguage2, mockLanguage3, mockLanguage4, mockLanguage5);
}
