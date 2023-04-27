package com.napier.sem.Queries;

import com.napier.sem.City;
import com.napier.sem.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PopulationSubsetCitiesQueryTest {

    /**
     * assert that the method getCitiesInDistrict() will equal the mock data
     */
    List<City> cities = MockData.getAllCitiesMock();
    List<Country> countries = MockData.getAllCountries();
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

    /**
     * assert that the method getCitiesInDistrict() will equal the mock data
     */
    @Test
    void expectDistrictsToEqualMockData() {
        assert(PopulationSubsetCitiesQuery.getCitiesInDistrict("District1", cities))
                .equals(MockData.getAllCitiesInDistrictMock());
    }

    /**
     * assert that the method getCitiesInCountry() will equal the mock data
     */
    @Test
    void expectCountriesToEqualMockData() {
        assert (PopulationSubsetCitiesQuery.getCitiesInCountry("Country1", cities, countries))
                .equals(MockData.getAllCitiesInCountriesMock());
    }

    /**
     * assert that the method getCitiesInRegion() will equal the mock data
     */
    @Test
    void expectRegionToEqualMockData() {
        assert (PopulationSubsetCitiesQuery.getCitiesInRegion("Region1", cities, countries))
                .equals(MockData.getAllCitiesInRegionsMock());
    }


    /**
     * assert that the method getCitiesInContinent() will equal the mock data
     */
    @Test
    void expectContinentToEqualMockData() {
        assert(PopulationSubsetCitiesQuery.getCitiesInContinent("Continent1", cities, countries))
                .equals(MockData.getAllCitiesInContinentsMock());
    }

    /**
     * assert that the method getCitiesLimitedBy() will equal the mock data
     */
    @Test
    void expectTopCitiesToEqualMockData() {
        assert (PopulationSubsetCitiesQuery.getCitiesLimitedBy(2, cities))
                .equals(MockData.getTopCitiesLimitedByMock());
    }

    /**
     * check that if the limit provided is larger than the subset, the subset will be returned
     */
    @Test
    void expectTopCitiesWithHighLimitToEqualMockData() {
        assert (PopulationSubsetCitiesQuery.getCitiesLimitedBy(15, MockData.getAllCitiesMock()))
                .equals(MockData.getAllCitiesMock());
    }

    /**
     * assert that report generation is as expected
     */
    @Test
    void expectReportToEqualMockData() {
        PopulationSubsetCitiesQuery.printReport("Header", "%-40s %-50s %-40s %-15s", cities);
        String dataString = "Header\n" +
                "Name                                     Country                                            District                                 Population     \n" +
                "City1                                    Country1                                           District1                                20             \n" +
                "City2                                    Country1                                           District2                                5              \n" +
                "City3                                    Country2                                           District1                                2              \n" +
                "City4                                    Country2                                           District1                                2              \n" +
                "City5                                    Country2                                           District2                                6              \n" +
                "City6                                    Country3                                           District1                                15             \n" +
                "City7                                    Country3                                           District1                                10             \n" +
                "City8                                    Country4                                           District1                                2              \n" +
                "City9                                    Country4                                           District2                                3              \n" +
                "City10                                   Country5                                           District1                                5              \n" +
                "City11                                   Country5                                           District1                                5              \n" +
                "City12                                   Country5                                           District2                                5              \n";
        String regex = "[\\r\\n]";
        assertEquals(dataString.replaceAll(regex, " "), outContent.toString().replaceAll(regex, " "));
    }

}

