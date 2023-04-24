package com.napier.sem;

import com.napier.sem.Queries.CountriesQueries;
import com.napier.sem.Queries.LanguagesQuery;
import com.napier.sem.Queries.MockData;
import com.napier.sem.Queries.Shared;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app = new App();
    Connection con = app.connect("localhost:33060");
    Statement statement = Shared.CreateStatement(con);

    String simpleQuery = "SELECT country.Code AS 'Code'," +
            "country.Name AS 'Name'," +
            "country.Continent AS 'Continent'," +
            "country.Region AS 'Region'," +
            "country.Population AS 'Population'," +
            "country.Capital AS 'Capital' " +
            "FROM country " +
            "GROUP BY country.Code " +
            "ORDER BY country.Population DESC ";

//    @Test
//    void assertErrorOnFailedStatement() {
//        ResultSet rset = CountriesQueries.getSqlResults(statement, simpleQuery);
//        assertEquals(" ", rset.getString(1));
//    }

    @Test
    void testCountryIsEqual() {
        List<Country> countries = CountriesQueries.getAllCountries(con);
        Country firstCountry = countries.get(0);
        Country testFirst = MockData.getFirstCountry();
        assertEquals(firstCountry.getCode(), testFirst.getCode());
    }
    @Test
    void testLanguage() {
        LanguagesQuery.queryLanguage(con);
    }
}
