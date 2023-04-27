package com.napier.sem.Queries;

import com.napier.sem.City;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapitalCitiesQueries {

    public static void getAllCapitalCityReports(Connection con, int limitBy, String region, String continent) {

        /**
         * get capital city data
         */
        List<City> allCapitalCities = getAllCapitalCities(con);
        List<City> capitalCitiesInContinent = getCapitalCitiesInContinent(continent, con);
        List<City> capitalCitiesInRegion = getCapitalCitiesInRegion(region, con);
        List<City> allCapitalCitiesLimited = getCapitalCitiesLimitedBy(limitBy, con);
        List<City> capitalCitiesInContinentLimited = getCapitalCitiesInContinentLimitedBy(limitBy, continent, con);
        List<City> capitalCitiesInRegionLimited = getCapitalCitiesInRegionLimitedBy(limitBy, region, con);

        /**
         * columns format
         */
        String format = "%-40s %-40s %-20s";

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
                Statement stmt = Shared.CreateStatement(con);

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
                    capitalCity.setCountry(rset.getString("Country"));
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
    public static List<City> getCapitalCitiesInContinent(String continent, Connection con) {
        List<City> capitalCitiesInContinent = new ArrayList<>();

        try {
            /**
             * creates an SQL statement
             */
            Statement stmt = Shared.CreateStatement(con);

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
                capitalCity.setCountry(rset.getString("Country"));
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
    public static List<City> getCapitalCitiesInRegion(String region, Connection con) {
        List<City> capitalCitiesInRegion = new ArrayList<>();

        try {

            /**
             * creates an SQL statement
             */
            Statement stmt = Shared.CreateStatement(con);

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
                capitalCity.setCountry(rset.getString("Country"));
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
    public static List<City> getCapitalCitiesLimitedBy(int n, Connection con) {
        List<City> capitalCitiesLimited = new ArrayList<>();

        try {
            /**
             * creates an SQL statement
             */
            Statement stmt = Shared.CreateStatement(con);

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
                capitalCity.setCountry(rset.getString("Country"));
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
    public static List<City> getCapitalCitiesInContinentLimitedBy(int n, String continent, Connection con) {
        List<City> capitalCitiesInContinentLimited = new ArrayList<>();

        try {
            /**
             * creates an SQL statement
             */
            Statement stmt = Shared.CreateStatement(con);

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
                capitalCity.setCountry(rset.getString("Country"));
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
    public static List<City> getCapitalCitiesInRegionLimitedBy(int n, String region, Connection con) {
        List<City> capitalCitiesInRegionLimited = new ArrayList<>();

        try {
            /**
             * creates an SQL statement
             */
            Statement stmt = Shared.CreateStatement(con);

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
                capitalCity.setCountry(rset.getString("Country"));
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
                "Name", "Country", "Population"));

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
                    capital.getCountry(),
                    capital.getPopulation()));
        }
    }
}
