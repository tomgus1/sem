package com.napier.sem.Queries;

import com.napier.sem.Country;

public class SampleData {
    /**
     * when we are running integration tests,
     * we might want to check that some data points are as expected
     *
     * Since a lot of our methods involve connecting to the database,
     * these should be integration tests using a sample data
     * to check the methods are working as intended
     * we could instead mock the database
     * but that's beyond the scope of this project
     *
     * A connection failure is mocked separately
     */

    static Country actualFirstCountry = new Country("CHN","China","Asia","Eastern Asia",1277558000,"1891");
    public static Country getFirstCountry() {
        return actualFirstCountry;
    }
}
