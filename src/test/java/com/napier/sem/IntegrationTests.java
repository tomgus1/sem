package com.napier.sem;

import com.napier.sem.Queries.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests {
    static App app = new App();
    Connection con = app.connect("localhost:33060");

    int n = 3;
    String continent = "Europe";
    String region = "Eastern Asia";
    String country = "Germany";
    String district = "England";
    String city = "London";

    /**
     * testing that a connection error results in an empty list
     */
    @Test
    void testConnectionError() {
        FakeConnection fakeConnection = new FakeConnection();
        List<Country> countries = CountriesQueries.getAllCountries(fakeConnection);
        assertEquals(Collections.emptyList(), countries);
    }

    /**
     * testing that a sql statement error results in an empty list
     */
    @Test
    void testSQLStatementError() {
        FakeConnection fakeConnection = new FakeConnection();
        fakeConnection.createStatementException = new SQLException();
        List<Country> countries = CountriesQueries.getAllCountries(fakeConnection);
        assertEquals(Collections.emptyList(), countries);
    }

    /**
     * testing an output of real data for countries is as expected
     */
    @Test
    void testCountryIsEqual() {
        List<Country> countries = CountriesQueries.getAllCountries(con);
        Country firstCountry = countries.get(0);
        Country testFirst = SampleData.getFirstCountry();
        assertEquals(firstCountry.getCode(), testFirst.getCode());
    }

    /**
     * these effectively just test that the app won't crash
     * increase test coverage
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
        CapitalCitiesQueries.getAllCapitalCityReports(con, n, region, continent);
    }

    @Test
    void testPopulation() {
        PopulationLivingQuery.populationLivingReportQuery(con);
    }

    @Test
    void testPopulationSubset() {
        PopulationSubsetCitiesQuery.populationCitiesInSubset(con, n, region, continent, country, district);
    }

    @Test
    void testTotalPopulations() {
        TotalPopulationQueries.getTotalPopulations(con, region, continent, country, district, city);
    }
}
