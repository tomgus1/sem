package com.napier.sem.Queries;

import com.napier.sem.App;
import com.napier.sem.City;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CapitalCitiesQueriesTest {

    /**
     * the list of all the capital cities (mock data)
     */
    List<City> capitalCity = MockData.getAllCapitalCities();
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
     * assert that report generation is as expected
     */
    @Test
    void expectReportToEqualMockData() {
        CapitalCitiesQueries.printReport("Header", "%-40s %-20s", capitalCity);
        String dataString = "Header\n" +
                "Name                                     Country             \n" +
                "Capital3                                 Country3            \n" +
                "Capital1                                 Country1            \n" +
                "Capital5                                 Country5            \n" +
                "Capital2                                 Country2            \n" +
                "Capital4                                 Country4            \n";
        String regex = "[\\r\\n]";
        assertEquals(dataString.replaceAll(regex, " "), outContent.toString().replaceAll(regex, " "));
    }
}
