package com.napier.sem.Queries;

import com.napier.sem.City;
import com.napier.sem.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapitalCitiesQueries {

    public static void getAllCapitalCityReports(Connection con) {

        //get country data
        List<City> allCapitalCities = getAllCapitalCities(con);
//        List<Country> capitalCitiesInContinent = getCapitalCitiesInContinent("Europe", allCapitalCities);
//        List<Country> capitalCitesInRegion = getCapitalCitiesInRegion("Eastern Asia", allCapitalCities);
//        List<Country> allCapitalCitiesLimited = getCapitalCitiesLimitedBy(3, allCapitalCities);
//        List<Country> capitalCitiesInContinentLimited = getCapitalCitiesLimitedBy(3, capitalCitiesInContinent);
//        List<Country> capitalCitiesInRegionLimited = getCapitalCitiesLimitedBy(3, capitalCitiesInRegion);

        //columns format
        String format = "%-10s %-50s %-20s %-40s %-15s %-15s";

        //report generation
        printReport(
                "All the capital cities in the world organised by largest population to smallest",
                format,
                allCapitalCities
        );
//        printReport(
//                "All the capital cities in a continent organised by largest population to smallest.",
//                format,
//                capitalCitiesInContinent
//        );
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
//                        "SELECT city.Name, country.Name, city.Population "
//                                + "FROM city JOIN country ON country.Code=Code "
//                                + "WHERE ID = Capital "
//                                + "ORDER BY city.Population DESC "
                        "SELECT city.Name, country.Name AS 'Country', city.Population "
                                + "FROM city JOIN country ON city.CountryCode = country.Code "
                                + "WHERE city.ID = country.Capital "
                                + "ORDER BY city.Population DESC "
                                );

                while (rset.next()) {
                    //map query result to capitalCity object and add to list
                    City capitalCity = new City();

                    capitalCity.setCountryCode(rset.getString("Code"));
                    capitalCity.setName(rset.getString("Name"));
//                    capitalCity.setContinent(rset.getString("Continent"));
//                    capitalCity.setRegion(rset.getString("Region"));
                    capitalCity.setPopulation(rset.getInt("Population"));
//                    capitalCity.setCapital(rset.getString("Capital"));

                    allCapitalCities.add(capitalCity);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to retrieve country details");
            }

            return allCapitalCities;
        }
    }
    //method to print a report from a list
    public static void printReport(String header, String format, List<City> list) {
        System.out.println(String.format(header));

        System.out.println(String.format(format,
                "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Loop over all countries in the list
        for (City capital : list)
        {
            if (capital == null) {
                System.out.println("Country is null");
                continue;
            }

            System.out.println(String.format(format,
                    capital.getCountryCode(),
                    capital.getName(),
//                    capital.getContinent(),
//                    capital.getRegion(),
                    capital.getPopulation()));
//                    capital.getCapital()));
        }
    }
}
