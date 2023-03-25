package com.napier.sem.Queries;

import com.napier.sem.Population;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PopulationLivingQuery {
    public static void populationLivingReportQuery(Connection con) {
        //get population data
        List<Population> populationCountry = getPopulationLivingCountry(con);
        List<Population> populationContinent = getPopulationLivingContinent(con);
        List<Population> populationRegion = getPopulationLivingRegion(con);

        //columns format
        String format = "%-40s %-20s %-25s %-25s %-25s %-25s";

        printReport(
                "The population of people, people living in cities, and people not living in cities in each country:",
                format,
                populationCountry

                );
        printReport(
                "The population of people, people living in cities, and people not living in cities in each Continent:",
                format,
                populationContinent
        );

        printReport(
                "The population of people, people living in cities, and people not living in cities in each region:",
                format,
                populationRegion

        );

    }
    public static List<Population> getPopulationLivingCountry(Connection con)
    {
        // Holds a list of queried results
        List<Population> allPopulations = new ArrayList<>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery("SELECT country.name AS 'Name', SUM(DISTINCT country.population) AS 'CountryPopulation', SUM(city.population) AS 'CityPopulation' "
                    + "FROM city JOIN country ON city.CountryCode = country.Code "
                    + "GROUP BY country.name ");

            while (rset.next())
            {
                // Define population
                Population population = new Population();

                // Extract data from SQL query result
                population.setName(rset.getString("Name"));
                population.setCountryPopulation(rset.getLong("CountryPopulation"));
                population.setCityPopulation(rset.getLong("CityPopulation"));

                // Add population to list
                allPopulations.add(population);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population info");
            return null;
        }
        return allPopulations;
    }
    public static List<Population> getPopulationLivingContinent(Connection con)
    {
        // Holds a list of queried results
        List<Population> allPopulations = new ArrayList<>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery( "SELECT country.continent AS 'Name', SUM(DISTINCT country.population) AS 'CountryPopulation', SUM(city.population) AS 'CityPopulation' "
                    + "FROM city JOIN country ON city.CountryCode = country.Code " +
                    "GROUP BY country.continent");

            while (rset.next())
            {
                // Define population
                Population population = new Population();

                // Extract data from SQL query result
                population.setName(rset.getString("Name"));
                population.setCountryPopulation(rset.getLong("CountryPopulation"));
                population.setCityPopulation(rset.getLong("CityPopulation"));

                // Add population to list
                allPopulations.add(population);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population info");
            return null;
        }
        return allPopulations;
    }

    public static List<Population> getPopulationLivingRegion(Connection con)
    {
        // Holds a list of queried results
        List<Population> allPopulations = new ArrayList<>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery("SELECT country.region AS 'Name', SUM(DISTINCT country.population) AS 'CountryPopulation', SUM(city.population) AS 'CityPopulation' "
                    + "FROM city JOIN country ON city.CountryCode = country.Code "
                    + "GROUP BY country.region ");

            while (rset.next())
            {
                // Define population
                Population population = new Population();

                // Extract data from SQL query result
                population.setName(rset.getString("Name"));
                population.setCountryPopulation(rset.getLong("CountryPopulation"));
                population.setCityPopulation(rset.getLong("CityPopulation"));

                // Add population to list
                allPopulations.add(population);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population info");
            return null;
        }
        return allPopulations;
    }

    public static void printReport(String header, String format, List<Population> list) {
        System.out.println(String.format(header));

        System.out.println(String.format(format,
                "Name", "Population", "Living In Cities", "Living Outside Cities", "Living In Cities (%)", "Living In Cities (%)"));


        // Loop over all countries in the list
        for (Population population : list)
        {
            if (population == null) {
                System.out.println("Population is null");
                continue;
            }
            // Calculations for living data
            float livingInPercent = ((float)population.getCityPopulation() / (float)population.getCountryPopulation()) * 100;
            float livingOutPercent = (1 - ((float)population.getCityPopulation() / (float)population.getCountryPopulation())) * 100;
            long livingOut = population.getCountryPopulation() - population.getCityPopulation();

            System.out.println(String.format(format,
                    population.getName(),
                    population.getCountryPopulation(),
                    population.getCityPopulation(),
                    livingOut,
                    livingInPercent,
                    livingOutPercent));
        }
    }
}
