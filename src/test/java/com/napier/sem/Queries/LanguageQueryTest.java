package com.napier.sem.Queries;

import com.napier.sem.Language;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

    public class LanguageQueryTest {

        //the list of all the languages (mock data)
        List<Language> languages = MockData.getAllLanguages();

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

        //assert that report generation is as expected
        @Test
        void expectReportToEqualMockData() {
            LanguagesQuery.printReport("Header", "%-10s %-15s %-20s", languages);
            String dataString = "Header" + lineEnd +
            "Language   Speakers        % Worldwide Speakers" + lineEnd +
            "Chinese    1191843539      19.61 " + lineEnd +
            "Hindi      405633070       6.67 " + lineEnd +
            "Spanish    355029462       5.84 " + lineEnd +
            "English    347077867       5.71 " + lineEnd +
            "Arabic     233839238       3.85 " ;
            assertEquals(dataString, outContent.toString());
        }

        //check that if a subset of countries is provided that doesn't exist, an empty report will be provided
               @Test
        void expectTopContinentsWithHighLimitToEqualMockData() {
            assert (CountriesQueries.getCountriesLimitedBy(10, MockData.getAllContinentsMock()))
                    .equals(MockData.getAllContinentsMock());
        }
    }


