package com.napier.sem.Queries;

import com.napier.sem.Population;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PopulationLivingQuery {
    public static ArrayList<Population> populationLivingReportQuery(String query, Connection con)
    {
        // Holds a list of queried results
        ArrayList<Population> populations = new ArrayList<Population>();

        // Check if query is null
        if (query == null || query == "")
        {
            System.out.println("Invalid query");
            return populations;
        }

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(query);

            while (rset.next())
            {
                // Define population
                Population population = new Population();

                // Extract data from SQL query result
                population.setName(rset.getString("Name"));
                population.setCountryPopulation(rset.getLong("CountryPopulation"));
                population.setCityPopulation(rset.getLong("CityPopulation"));

                // Add population to list
                populations.add(population);
            }

            // Return global population
            return populations;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population info");
            return null;
        }
    }

    public static void printPopulationLiving(ArrayList<Population> populations, Connection con)
    {

        if (populations == null || populations.isEmpty())
        {
            System.out.println("No populations");
            return;
        }
        // Print header
        System.out.println(String.format("%-40s %-20s %-25s %-25s %-25s %-25s",
                "Name", "Population", "Living In Cities", "Living Outside Cities", "Living In Cities (%)", "Living In Cities (%)"));
        // Loop over all populations in the list
        for (Population population : populations)
        {
            if (population == null)
                continue;

            // Calculations for living data
            float livingInPercent = ((float)population.getCityPopulation() / (float)population.getCountryPopulation()) * 100;
            float livingOutPercent = (1 - ((float)population.getCityPopulation() / (float)population.getCountryPopulation())) * 100;
            long livingOut = population.getCountryPopulation() - population.getCityPopulation();

            System.out.println(String.format("%-40s %-20s %-25s %-25s %-25.2f %-25.2f", population.getName(), population.getCountryPopulation(), population.getCityPopulation(), livingOut, livingInPercent, livingOutPercent));
        }
    }
    public static ArrayList<Population> populationLivingReportCountry(Connection con)
    {
        System.out.println("The population of people, people living in cities, and people not living in cities in each country:");
        return populationLivingReportQuery(
                "SELECT country.name AS 'Name', SUM(DISTINCT country.population) AS 'CountryPopulation', SUM(city.population) AS 'CityPopulation' "
                        + "FROM city JOIN country ON city.CountryCode = country.Code "
                        + "GROUP BY country.name ", con);
    }
    public static ArrayList<Population> populationLivingReportContinent(Connection con)
    {
        System.out.println("The population of people, people living in cities, and people not living in cities in each continent:");
        return populationLivingReportQuery(
                "SELECT country.continent AS 'Name', SUM(DISTINCT country.population) AS 'CountryPopulation', SUM(city.population) AS 'CityPopulation' "
                        + "FROM city JOIN country ON city.CountryCode = country.Code "
                        + "GROUP BY country.continent ", con);
    }

    public static ArrayList<Population> populationLivingReportRegion(Connection con) {
        System.out.println("The population of people, people living in cities, and people not living in cities in each region:");
        return populationLivingReportQuery(
                "SELECT country.region AS 'Name', SUM(DISTINCT country.population) AS 'CountryPopulation', SUM(city.population) AS 'CityPopulation' "
                        + "FROM city JOIN country ON city.CountryCode = country.Code "
                        + "GROUP BY country.region ", con);
    }
}
