package com.napier.sem.Queries;

import com.napier.sem.City;
import com.napier.sem.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PopulationSubsetCitiesQueryTest {

    //the list of all the cities/countries (mock data)
    List<City> cities = MockData.getAllCitiesMock();
    List<Country> countries = MockData.getAllCountries();

    private final String os = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
    private final String lineEnd = os.startsWith("win") ? "\r\n" : "\n";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    //assert that the method getCitiesInDistrict() will equal the mock data
    @Test
    void expectDistrictsToEqualMockData() {
        assert(PopulationSubsetCitiesQuery.getCitiesInDistrict("District1", cities))
                .equals(MockData.getAllCitiesInDistrictMock());
    }

    //assert that the method getCitiesInCountry() will equal the mock data
    @Test
    void expectCountriesToEqualMockData() {
        assert(PopulationSubsetCitiesQuery.getCitiesInCountry("Country1", cities, countries))
                .equals(MockData.getAllCitiesInCountriesMock());
    }

    //assert that the method getCitiesInRegion() will equal the mock data
    @Test
    void expectRegionToEqualMockData() {
        assert(PopulationSubsetCitiesQuery.getCitiesInRegion("Region1", cities, countries))
                .equals(MockData.getAllCitiesInRegionsMock());
    }

    //assert that the method getCitiesInContinent() will equal the mock data
    @Test
    void expectContinentToEqualMockData() {
        assert(PopulationSubsetCitiesQuery.getCitiesInContinent("Continent2", cities, countries))
                .equals(MockData.getAllCitiesInContinentsMock());
    }

    //assert that the method getCitiesLimitedBy() will equal the mock data
    @Test
    void expectTopCitiesToEqualMockData() {
        assert (PopulationSubsetCitiesQuery.getCitiesLimitedBy(4, cities))
                .equals(MockData.getTopCitiesMock());
    }

}

