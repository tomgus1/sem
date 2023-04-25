package com.napier.sem.Queries;

import com.napier.sem.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesQueries {

    //todo, add user input for continent, region, limit
    public static void getAllCountryReports(Connection con) {
        //get country data
        List<Country> allCountries = getAllCountries(con);
        List<Country> countriesInContinent = getCountriesInContinent("Europe", allCountries);
        List<Country> countriesInRegion = getCountriesInRegion("Eastern Asia", allCountries);
        List<Country> allCountriesLimited = getCountriesLimitedBy(3, allCountries);
        List<Country> countriesInContinentLimited = getCountriesLimitedBy(3, countriesInContinent);
        List<Country> countriesInRegionLimited = getCountriesLimitedBy(3, countriesInRegion);

        //columns format
        String format = "%-10s %-50s %-20s %-40s %-15s %-15s";

        //report generation
        printReport(
                "All the countries in the world organised by largest population to smallest",
                format,
                allCountries
        );
        printReport(
                "All the countries in a continent organised by largest population to smallest.",
                format,
                countriesInContinent
        );
        printReport(
                "All the countries in a region organised by largest population to smallest.",
                format,
                countriesInRegion
        );
        printReport(
                "The top N populated countries in the world where N is provided by the user.",
                format,
                allCountriesLimited
        );
        printReport(
                "The top N populated countries in a continent where N is provided by the user.",
                format,
                countriesInContinentLimited
        );
        printReport(
                "The top N populated countries in a region where N is provided by the user.",
                format,
                countriesInRegionLimited
        );
    }

    public static ResultSet getSqlResults(Statement stmt, String query) {
        //create a SQL statement
        ResultSet rset = null;

        try {
            //execute SQL statement
            rset = stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve query details from database");
        }

        return rset;
    }

    public static List<Country> setResultToCountryList(ResultSet rset) {
        List<Country> countries = new ArrayList<>();

        try {
            while (rset.next()) {
                //map query result to country object and add to list
                Country country = new Country();

                country.setCode(rset.getString("Code"));
                country.setName(rset.getString("Name"));
                country.setContinent(rset.getString("Continent"));
                country.setRegion(rset.getString("Region"));
                country.setPopulation(rset.getInt("Population"));
                country.setCapital(rset.getString("Capital"));

                countries.add(country);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to set country details to list");
        }

        return countries;
    }

    public static List<Country> getAllCountries(Connection con) {
        Statement statement = Shared.CreateStatement(con);
        String query = "SELECT country.Code AS 'Code'," +
                "country.Name AS 'Name'," +
                "country.Continent AS 'Continent'," +
                "country.Region AS 'Region'," +
                "country.Population AS 'Population'," +
                "country.Capital AS 'Capital' " +
                "FROM country " +
                "GROUP BY country.Code " +
                "ORDER BY country.Population DESC ";

        ResultSet rset = getSqlResults(statement, query);

        return setResultToCountryList(rset);
    }

    //use list of all countries to get countries in a continent
    public static List<Country> getCountriesInContinent(String continent, List<Country> countries) {
        List<Country> countriesInContinent = new ArrayList<>();

        for (Country country : countries) {
            if (country.getContinent().equals(continent)) {
                countriesInContinent.add(country);
            }
        }

        return countriesInContinent;
    }

    //use list of all countries to get countries in a region
    public static List<Country> getCountriesInRegion(String region, List<Country> countries) {
        List<Country> countriesInRegion = new ArrayList<>();

        for (Country country : countries) {
            if (country.getRegion().equals(region)) {
                countriesInRegion.add(country);
            }
        }

        return countriesInRegion;
    }

    //use list to get top n countries to be specified by user
    public static List<Country> getCountriesLimitedBy(int limit, List<Country> countries) {
        List<Country> countriesLimited = new ArrayList<>();

        if (limit > countries.size()) {
            limit = countries.size();
        }

        for (int i = 0; i < limit; i++) {
            countriesLimited.add(countries.get(i));
        }

        return countriesLimited;
    }

    //method to print a report from a list
    public static void printReport(String header, String format, List<Country> list) {
        System.out.println(String.format(header));

        System.out.println(String.format(format,
                "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Loop over all countries in the list
        for (Country country : list) {
            if (country == null) {
                System.out.println("Country is null");
                continue;
            }

            System.out.println(String.format(format,
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital()));
        }
    }
}
