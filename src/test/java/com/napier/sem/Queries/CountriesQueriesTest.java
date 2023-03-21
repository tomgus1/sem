package com.napier.sem.Queries;

import com.napier.sem.Country;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountriesQueriesTest {

    //the list of all the countries (mock data)
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

    //assert that the method getCountriesInContinent() will equal the mock data
    @Test
    void expectContinentsToEqualMockData() {
        assert (CountriesQueries.getCountriesInContinent("Continent2", countries))
                .equals(MockData.getSingleContinentMock());
    }

    //assert that the method getCountriesInRegion() will equal the mock data
    @Test
    void expectRegionsToEqualMockData() {
        assert (CountriesQueries.getCountriesInRegion("Region2", countries))
                .equals(MockData.getSingleRegionMock());
    }

    //assert that the method getCountriesLimitedBy() will equal the mock data
    @Test
    void expectTopCountriesToEqualMockData() {
        assert (CountriesQueries.getCountriesLimitedBy(3, countries))
                .equals(MockData.getTopCountriesMock());
    }

    //assert that report generation is as expected
    @Test
    void expectReportToEqualMockData() {
        CountriesQueries.printReport("Header", "%-10s %-50s %-20s %-40s %-15s %-15s", countries);
        String dataString = "Header" + lineEnd +
                "Code       Name                                               Continent            Region                                   Population      Capital        " + lineEnd +
                "GHI        Country3                                           Continent2           Region2                                  30              Capital3       " + lineEnd +
                "ABC        Country1                                           Continent1           Region1                                  20              Capital1       " + lineEnd +
                "MNO        Country5                                           Continent2           Region2                                  15              Capital5       " + lineEnd +
                "DEF        Country2                                           Continent1           Region1                                  10              Capital2       " + lineEnd +
                "JKL        Country4                                           Continent2           Region2                                  5               Capital4       " + lineEnd;
        assertEquals(dataString, outContent.toString());
    }

    //check that if a subset of countries is provided that doesn't exist, an empty report will be provided
    @Test
    void expectReportToBeBlank() {
        CountriesQueries.printReport("Header", "%-10s %-50s %-20s %-40s %-15s %-15s", CountriesQueries.getCountriesInContinent("Random", this.countries));
        String dataString = "Header" + lineEnd +
                "Code       Name                                               Continent            Region                                   Population      Capital        " + lineEnd;
        assertEquals(dataString, outContent.toString());
    }

    //check that if the limit provided is larger than the subset, the subset will be returned
    @Test
    void expectTopContinentsWithHighLimitToEqualMockData() {
        assert (CountriesQueries.getCountriesLimitedBy(10, MockData.getSingleContinentMock()))
                .equals(MockData.getSingleContinentMock());
    }
}
