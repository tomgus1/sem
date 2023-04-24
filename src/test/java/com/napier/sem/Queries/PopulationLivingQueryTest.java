package com.napier.sem.Queries;

import com.napier.sem.Population;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PopulationLivingQueryTest {
    List<Population> populationCountry = MockData.getAllPopulationCountry();

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
        PopulationLivingQuery.printReport( "Header", "%-40s %-20s %-25s %-25s %-25s %-25s", populationCountry) ;
        String dataString = "Header" + lineEnd +
                "Name                                     Population           Living In Cities          Living Outside Cities     Living In Cities (%)      Living Outside Cities (%)" + lineEnd +
                "Country 1                                1000000              105400                    894600                    10.54                     89.46                    " + lineEnd +
                "Country 2                                2000000              204500                    1795500                   10.225                    89.775                   " + lineEnd +
                "Country 3                                3000000              345355                    2654645                   11.511833                 88.48817                 " + lineEnd +
                "Country 4                                40000500             5555434                   34445066                  13.8884115                86.11159                 " + lineEnd +
                "Country 5                                143345               5000                      138345                    3.4880881                 96.51191                 " + lineEnd;
        assertEquals(dataString, outContent.toString());
    }
}
