package com.napier.sem;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountriesQueryTest {

    @Test
    void testConnectionError() {
        FakeConnection fakeConnection = new FakeConnection();
        fakeConnection.createStatementException = new SQLException();

        CountriesQuery.CountryDataSource dataSource = new CountriesQuery.CountryDataSource(fakeConnection);

        List<Country> actual = dataSource.getCountries();

        assertEquals(Collections.emptyList(), actual);
    }
}