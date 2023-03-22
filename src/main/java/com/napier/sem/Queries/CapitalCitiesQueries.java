package com.napier.sem.Queries;

import com.napier.sem.City;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapitalCitiesQueries {

    public static void getAllCapitalCityReports(Connection con) {

        /**
         * get capital city data
         */
        List<City> allCapitalCities = getAllCapitalCities(con);
        List<City> capitalCitiesInContinent = getCapitalCitiesInContinent(con,"Europe", allCapitalCities);
        List<City> capitalCitiesInRegion = getCapitalCitiesInRegion(con,"Eastern Asia", allCapitalCities);
        List<City> allCapitalCitiesLimited = getCapitalCitiesLimitedBy(con, 3, allCapitalCities);
        List<City> capitalCitiesInContinentLimited = getCapitalCitiesInContinentLimitedBy(con,3, "Europe", capitalCitiesInContinent);
        List<City> capitalCitiesInRegionLimited = getCapitalCitiesInRegionLimitedBy(con, 3, "Eastern Asia", capitalCitiesInRegion);

        /**
         * columns format
         */
        String format = "%-40s %-20s";

        /**
         * report generation
         */
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
        printReport(
                "All the capital cities in a region organised by largest population to smallest.",
                format,
                capitalCitiesInRegion
        );
        printReport(
                "The top N populated capital cities in the world where N is provided by the user.",
                format,
                allCapitalCitiesLimited
        );
        printReport(
                "The top N populated capital cities in a continent where N is provided by the user.",
                format,
                capitalCitiesInContinentLimited
        );
        printReport(
                "The top N populated capital cities in a region where N is provided by the user.",
                format,
                capitalCitiesInRegionLimited
        );
    }

    public static List<City> getAllCapitalCities(Connection con) {
        /**
         * create list to hold data
         */
        List<City> allCapitalCities = new ArrayList<>();
        {

            try {
                /**
                 * creates an SQL statement
                 */
                Statement stmt = con.createStatement();

                /**
                 * sends SQL statement to the database
                 */
                ResultSet rset = stmt.executeQuery(
                        "SELECT city.Name, country.Name AS Country, city.Population "
                        + "FROM country country "
                        + "LEFT JOIN city city on country.Capital = city.ID "
                        + "ORDER BY city.Population DESC ");

                while (rset.next()) {
                    /**
                     *map query result to capitalCity object and add to list
                     */
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

    /**
     * list all capital cities in a continent
     */
    public static List<City> getCapitalCitiesInContinent(Connection con, String continent, List<City> cities) {
        List<City> capitalCitiesInContinent = new ArrayList<>();

        try {

            /**
             * creates an SQL statement
             */
            Statement stmt = con.createStatement();

            /**
             * sends SQL statement to the database
             */
            ResultSet rset = stmt.executeQuery(
                        "SELECT city.Name, country.Name AS Country, city.Population "
                                + "FROM country country "
                                + "LEFT JOIN city city ON country.Capital = city.ID "
                                + "WHERE country.Continent = '"+ continent +"' "
                                + "ORDER BY city.Population DESC ");

            while (rset.next()) {
                /**
                 * map query result to capitalCity object and add to list
                 */
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

    /**
     * list of all capital cities in a region
     */
    public static List<City> getCapitalCitiesInRegion(Connection con, String region, List<City> cities) {
        List<City> capitalCitiesInRegion = new ArrayList<>();

        try {

            /**
             * creates an SQL statement
             */
            Statement stmt = con.createStatement();

            /**
             * sends SQL statement to the database
             */
            ResultSet rset = stmt.executeQuery(
                    "SELECT city.Name, country.Name AS Country, city.Population "
                            + "FROM country country "
                            + "LEFT JOIN city city ON country.Capital = city.ID "
                            + "WHERE country.Region = '"+ region +"' "
                            + "ORDER BY city.Population DESC ");

            while (rset.next()) {
                /**
                 * map query result to capitalCity object and add to list
                 */
                City capitalCity = new City();

                capitalCity.setName(rset.getString("Name"));
                capitalCity.setPopulation(rset.getInt("Population"));

                capitalCitiesInRegion.add(capitalCity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve capital city details");
        }
        return capitalCitiesInRegion;
    }

    /**
     * list top n capital cities in the world
     */
    public static List<City> getCapitalCitiesLimitedBy(Connection con, int n, List<City> cities) {
        List<City> capitalCitiesLimited = new ArrayList<>();

        if (n < 1) {
            throw new NullPointerException("N must be greater than 0");
        }

        try {
            /**
             * creates an SQL statement
             */
            Statement stmt = con.createStatement();

            /**
             * sends the SQL statement to the database
             */
            ResultSet rset = stmt.executeQuery(
                    "SELECT city.Name, country.Name AS Country, city.Population "
                            + "FROM country country "
                            + "LEFT JOIN city city ON country.Capital = city.ID "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + n + " ");

            while (rset.next()) {
                /**
                 * map query result to capitalCity object and add to list
                 */
                City capitalCity = new City();

                capitalCity.setName(rset.getString("Name"));
                capitalCity.setPopulation(rset.getInt("Population"));

                capitalCitiesLimited.add(capitalCity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve capital city details");
        }
        return capitalCitiesLimited;
    }

    /**
     * list top n capital cities in a continent
     */
    public static List<City> getCapitalCitiesInContinentLimitedBy(Connection con, int n, String continent, List<City> cities) {
        List<City> capitalCitiesInContinentLimited = new ArrayList<>();

        if (n < 1) {
            throw new NullPointerException("N must be greater than 0");
        }

        try {
            /**
             * creates an SQL statement
             */
            Statement stmt = con.createStatement();

            /**
             * sends the SQL statement to the database
             */
            ResultSet rset = stmt.executeQuery(
                    "SELECT city.Name, country.Name AS Country, city.Population "
                            + "FROM country country "
                            + "LEFT JOIN city city ON country.Capital = city.ID "
                            + "WHERE country.Continent = '"+ continent +"' "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + n + ";" );

            while (rset.next()) {
                /**
                 * map query result to capitalCity object and add to list
                 */
                City capitalCity = new City();

                capitalCity.setName(rset.getString("Name"));
                capitalCity.setPopulation(rset.getInt("Population"));

                capitalCitiesInContinentLimited.add(capitalCity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve capital city details");
        }
        return capitalCitiesInContinentLimited;
    }

    /**
     * list top n capital cities in a region
     */
    public static List<City> getCapitalCitiesInRegionLimitedBy(Connection con, int n, String region, List<City> cities) {
        List<City> capitalCitiesInRegionLimited = new ArrayList<>();

        if (n < 1) {
            throw new NullPointerException("N must be greater than 0");
        }

        try {
            /**
             * creates an SQL statement
             */
            Statement stmt = con.createStatement();

            /**
             * sends SQL statement to the database
             */
            ResultSet rset = stmt.executeQuery(
                    "SELECT city.Name, country.Name AS Country, city.Population "
                            + "FROM country country "
                            + "LEFT JOIN city city ON country.Capital = city.ID "
                            + "WHERE country.Region = '"+ region +"' "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + n + ";" );

            while (rset.next()) {
                /**
                 * map query result to capitalCity object and add to list
                 */
                City capitalCity = new City();

                capitalCity.setName(rset.getString("Name"));
                capitalCity.setPopulation(rset.getInt("Population"));

                capitalCitiesInRegionLimited.add(capitalCity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve capital city details");
        }
        return capitalCitiesInRegionLimited;
    }

    /**
     * method to print a report from a list
     */
    public static void printReport(String header, String format, List<City> list) {
        System.out.println(String.format(header));

        System.out.println(String.format(format,
                "Name", "Population"));

        /**
         * create a loop to print all capital cities in the list
         */
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
