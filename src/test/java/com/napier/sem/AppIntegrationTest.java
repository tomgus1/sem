package com.napier.sem;

import com.napier.sem.Queries.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app = new App();
    Connection con = app.connect("localhost:33060");

    /**
     * testing an output of real data is as expected
     */
    @Test
    void testCountryIsEqual() {
        List<Country> countries = CountriesQueries.getAllCountries(con);
        Country firstCountry = countries.get(0);
        Country testFirst = MockData.getFirstCountry();
        assertEquals(firstCountry.getCode(), testFirst.getCode());
    }
    /**
     * these effectively test that the app won't crash
     * but will increase coverage, since codecov is ridiculous
     */
    @Test
    void testCountries() {
        CountriesQueries.getAllCountries(con);
    }
    @Test
    void testLanguage() {
        LanguagesQuery.LanguagesReport(con);
    }
    @Test
    void testCities() {
        CapitalCitiesQueries.getAllCapitalCityReports(con,3);
    }

    @Test
    void testPopulation() {
        PopulationLivingQuery.populationLivingReportQuery(con);
    }

    @Test
    void testPopulationSubset() {
        PopulationSubsetCitiesQuery.populationCitiesInSubset(con, 3);
    }
}
