package com.napier.sem.Queries;

import com.napier.sem.City;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapitalCitiesQueries {

    public static void getAllCapitalCityReports(Connection con) {

        //get capital city data
        List<City> allCapitalCities = getAllCapitalCities(con);
        List<City> capitalCitiesInContinent = getCapitalCitiesInContinent(con,"Europe", allCapitalCities);
//        List<City> capitalCitesInRegion = getCapitalCitiesInRegion(con,"Eastern Asia", allCapitalCities);
//        List<Country> allCapitalCitiesLimited = getCapitalCitiesLimitedBy(3, allCapitalCities);
//        List<Country> capitalCitiesInContinentLimited = getCapitalCitiesLimitedBy(3, capitalCitiesInContinent);
//        List<Country> capitalCitiesInRegionLimited = getCapitalCitiesLimitedBy(3, capitalCitiesInRegion);

        //columns format
        String format = "%-40s %-20s";

        //report generation
        printReport(
                "All the capital cities in the world organised by largest population to smallest",
                format,
                allCapitalCities
        );
        printReport(
                "All the capital cities in a continent organised by largest population to smallest.",
                format,
                capitalCitiesInContinent
        );
//        printReport(
//                "All the capital cities in a region organised by largest population to smallest.",
//                format,
//                capitalCitiesInRegion
//        );
//        printReport(
//                "The top N populated countries in the world where N is provided by the user.",
//                format,
//                allCountriesLimited
//        );
//        printReport(
//                "The top N populated countries in a continent where N is provided by the user.",
//                format,
//                countriesInContinentLimited
//        );
//        printReport(
//                "The top N populated countries in a region where N is provided by the user.",
//                format,
//                countriesInRegionLimited
//        );
    }

    public static List<City> getAllCapitalCities(Connection con) {
        //create list to hold data
        List<City> allCapitalCities = new ArrayList<>();
        {

            try {
                // Creates an SQL statement.
                Statement stmt = con.createStatement();

                // Sends the SQL statement to the database.
                ResultSet rset = stmt.executeQuery(
                        "SELECT city.Name, country.Name AS Country, city.Population "
                        + "FROM country country "
                        + "LEFT JOIN city city on country.Capital = city.ID "
                        + "ORDER BY city.Population DESC ");

                while (rset.next()) {
                    //map query result to capitalCity object and add to list
                    City capitalCity = new City();

                    capitalCity.setName(rset.getString("Name"));
                    capitalCity.setPopulation(rset.getInt("Population"));

                    allCapitalCities.add(capitalCity);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to retrieve capital city details");
            }

            return allCapitalCities;
        }
    }

    //list all capital cities in a continent
    public static List<City> getCapitalCitiesInContinent(Connection con, String continent, List<City> cities) {
        List<City> capitalCitiesInContinent = new ArrayList<>();

        try {

            // Creates an SQL statement.
            Statement stmt = con.createStatement();

            // Sends the SQL statement to the database.
            ResultSet rset = stmt.executeQuery(
                        "SELECT city.Name, country.Name AS Country, city.Population "
                                + "FROM country country "
                                + "LEFT JOIN city city ON country.Capital = city.ID "
                                + "WHERE country.Continent = '"+ continent +"' "
                                + "ORDER BY city.Population DESC ");

            while (rset.next()) {
                //map query result to capitalCity object and add to list
                City capitalCity = new City();

                capitalCity.setName(rset.getString("Name"));
                capitalCity.setPopulation(rset.getInt("Population"));

                capitalCitiesInContinent.add(capitalCity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve capital city details");
        }
        return capitalCitiesInContinent;
    }

    //method to print a report from a list
    public static void printReport(String header, String format, List<City> list) {
        System.out.println(String.format(header));

        System.out.println(String.format(format,
                "Name", "Population"));

        // Loop over all capital cities in the list
        for (City capital : list)
        {
            if (capital == null) {
                System.out.println("Capital City is null");
                continue;
            }

            System.out.println(String.format(format,
                    capital.getName(),
                    capital.getPopulation()));
        }
    }
}
