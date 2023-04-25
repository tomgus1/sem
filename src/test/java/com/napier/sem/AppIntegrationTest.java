package com.napier.sem;

import com.napier.sem.Queries.CountriesQueries;
import com.napier.sem.Queries.LanguagesQuery;
import com.napier.sem.Queries.MockData;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app = new App();
    Connection con = app.connect("localhost:33060");

    @Test
    void testCountryIsEqual() {
        List<Country> countries = CountriesQueries.getAllCountries(con);
        Country firstCountry = countries.get(0);
        Country testFirst = MockData.getFirstCountry();
        assertEquals(firstCountry.getCode(), testFirst.getCode());
    }
    @Test
    void testLanguage() {
        LanguagesQuery.LanguagesReport(con);
    }
}
