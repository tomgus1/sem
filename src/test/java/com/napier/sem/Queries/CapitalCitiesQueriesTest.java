package com.napier.sem.Queries;

import com.napier.sem.City;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CapitalCitiesQueriesTest {

    //the list of all the capital cities (mock data)
    List<City> capitalCity = MockData.getAllCapitalCities();
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

    //assert that the method getCapitalCitiesInContinent() will equal the mock data
    @Test
    void expectContinentsToEqualMockData() {
        assert (CapitalCitiesQueries.getCapitalCitiesInContinent("Continent3", capitalCity))
                .equals(MockData.getCapitalCitiesInContinentMock());
    }

    //assert that the method getCapitalCitiesInRegion() will equal the mock data
    @Test
    void expectRegionsToEqualMockData() {
        assert (CapitalCitiesQueries.getCapitalCitiesInRegion("Region2", capitalCity))
                .equals(MockData.getCapitalCitiesInRegionMock());
    }

    //assert that the method getCapitalCitiesLimitedBy() will equal the mock data
    @Test
    void expectTopCapitalCitiesToEqualMockData() {
        assert (CapitalCitiesQueries.getCapitalCitiesLimitedBy(3, capitalCity))
                .equals(MockData.getTopCapitalCitiesMock());
    }

    //assert that report generation is as expected
    @Test
    void expectReportToEqualMockData() {
        CapitalCitiesQueries.printReport("Header", "%-40s %-20s", capitalCity);
        String dataString = "Header" + lineEnd +
                "Name                                     Population          " + lineEnd +
                "Capital3                                 30                  " + lineEnd +
                "Capital1                                 20                  " + lineEnd +
                "Capital5                                 15                  " + lineEnd +
                "Capital2                                 10                  " + lineEnd +
                "Capital4                                 5                   " + lineEnd;
        assertEquals(dataString, outContent.toString());
    }

    //check that if a subset of capital cities is provided that doesn't exist, an empty report will be provided
    @Test
    void expectReportToBeBlank() {
        CapitalCitiesQueries.printReport("Header", "%-40s %-20s", CapitalCitiesQueries.getCapitalCitiesInContinent("Random", this.capitalCity));
        String dataString = "Header" + lineEnd +
                "Name                                     Population          " + lineEnd;
        assertEquals(dataString, outContent.toString());
    }

    //check that if the limit provided is larger than the subset, the subset will be returned
    @Test
    void expectTopContinentsWithHighLimitToEqualMockData() {
        assert (CapitalCitiesQueries.getCapitalCitiesLimitedBy(10, MockData.getCapitalCitiesInContinentMock()))
                .equals(MockData.getCapitalCitiesInContinentMock());
    }
}
