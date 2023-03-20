package com.napier.sem.Queries;

import com.napier.sem.Country;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountriesQueriesTest {

    //the list of all the countries (mock data)
    List<Country> countries = MockData.getAllCountries();

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

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

    @Test
    void expectReportToEqualMockData() {
        CountriesQueries.printReport("Header", "%-10s %-50s %-20s %-40s %-15s %-15s", countries);
        String dataString = "Header\n" +
                "Code       Name                                               Continent            Region                                   Population      Capital        \n" +
                "GHI        Country3                                           Continent2           Region2                                  30              Capital3       \n" +
                "MNO        Country5                                           Continent2           Region2                                  15              Capital5       \n" +
                "ABC        Country1                                           Continent1           Region1                                  20              Capital1       \n" +
                "DEF        Country2                                           Continent1           Region1                                  10              Capital2       \n" +
                "JKL        Country4                                           Continent2           Region2                                  5               Capital4       \n";
        assertEquals(dataString, outContent.toString());
    }
}
