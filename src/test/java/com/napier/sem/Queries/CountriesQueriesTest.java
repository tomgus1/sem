package com.napier.sem.Queries;

import com.napier.sem.Country;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CountriesQueriesTest {

    //the list of all the countries (mock data)
    List<Country> countries = MockData.getAllCountries();

    //assert that the method getCountriesInContinent() will equal the mock data
    @Test
    void expectContinentsToEqualMockData() {
        assert(CountriesQueries.getCountriesInContinent("Continent2", countries))
                .equals(MockData.getAllContinentsMock());
    }

    //assert that the method getCountriesInRegion() will equal the mock data
    @Test
    void expectRegionsToEqualMockData() {
        assert(CountriesQueries.getCountriesInRegion("Region2", countries))
                .equals(MockData.getAllRegionsMock());
    }

    //assert that the method getCountriesLimitedBy() will equal the mock data
    @Test
    void expectTopCountriesToEqualMockData() {
        assert(CountriesQueries.getCountriesLimitedBy(3, countries))
                .equals(MockData.getTopCountriesMock());
    }
}
