package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {
    //Connection to MySQL database.
    private Connection con = null;

    public void queryLanguage()
    {
        // Holds a list of queried results
        ArrayList<Language> languages = new ArrayList<Language>();

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(
                    "SELECT countrylanguage.Language AS 'Language', (SUM((country.Population * countrylanguage.Percentage) / 100)) AS 'Population', (((SUM((country.Population * countrylanguage.Percentage) / 100)) * 100) / (SELECT SUM(country.Population) FROM country)) AS 'PercentageGlobal' "
                            + "FROM countrylanguage, country "
                            + "WHERE (countrylanguage.Language = 'Chinese' "
                            + "OR countrylanguage.Language = 'English' "
                            + "OR countrylanguage.Language = 'Hindi' "
                            + "OR countrylanguage.Language = 'Spanish' "
                            + "OR countrylanguage.Language = 'Arabic') "
                            + "AND countrylanguage.CountryCode = country.Code "
                            + "GROUP BY countrylanguage.Language "
                            + "ORDER BY Population DESC ");

            while (rset.next())
            {
                // Define population
                Language language = new Language();

                // Extract data from SQL query result
                language.setLanguage(rset.getString("Language"));
                language.setPopulation(rset.getLong("Population"));
                language.setPercentage(rset.getFloat("PercentageGlobal"));

                // Add population to list
                languages.add(language);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population details");
        }

        // Print header
        System.out.println(String.format("%-10s %-15s %-20s",
                "Language", "Speakers", "% Worldwide Speakers"));

        // Loop over all languages in the list
        for (Language language : languages)
        {
            if (language == null)
                continue;

            System.out.println(String.format("%-10s %-15s %-20.2f",
                    language.getLanguage(), language.getPopulation(), language.getPercentage()));
        }
    }

    public ArrayList<Population> populationReportQuery(String query)
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
    public static void printPopulationLiving(ArrayList<Population> populations)
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
    public ArrayList<Population> populationReportCountry()
    {
        System.out.println("The population of people, people living in cities, and people not living in cities in each country:");
        return populationReportQuery(
                "SELECT country.name AS 'Name', SUM(DISTINCT country.population) AS 'CountryPopulation', SUM(city.population) AS 'CityPopulation' "
                        + "FROM city JOIN country ON city.CountryCode = country.Code "
                        + "GROUP BY country.name ");
    }
    public ArrayList<Population> populationReportContinent()
    {
        System.out.println("The population of people, people living in cities, and people not living in cities in each continent:");
        return populationReportQuery(
                "SELECT country.continent AS 'Name', SUM(DISTINCT country.population) AS 'CountryPopulation', SUM(city.population) AS 'CityPopulation' "
                        + "FROM city JOIN country ON city.CountryCode = country.Code "
                        + "GROUP BY country.continent ");
    }

    public ArrayList<Population> populationReportRegion()
    {
        System.out.println("The population of people, people living in cities, and people not living in cities in each region:");
        return populationReportQuery(
                "SELECT country.region AS 'Name', SUM(DISTINCT country.population) AS 'CountryPopulation', SUM(city.population) AS 'CityPopulation' "
                        + "FROM city JOIN country ON city.CountryCode = country.Code "
                        + "GROUP BY country.region ");
    }

    //Connect to the MySQL database.
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        //Catching an exception
        catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }
        //connection to the DB
        int retries = 10; //setting a counter to decrease
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    // Disconnect from the MySQL database.
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) //Catching an exception
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // The population of people, people living in cities, and people not living in cities in each country
        ArrayList<Population> countryPopulations = a.populationReportCountry();
        a.printPopulationLiving(countryPopulations);

        // The population of people, people living in cities, and people not living in cities in each continent
        ArrayList<Population> continentPopulations = a.populationReportContinent();
        a.printPopulationLiving(continentPopulations);

        // The population of people, people living in cities, and people not living in cities in each region
        ArrayList<Population> regionPopulations = a.populationReportRegion();
        a.printPopulationLiving(regionPopulations);



        a.queryLanguage();

        // Disconnect from database
        a.disconnect();
    }

}
