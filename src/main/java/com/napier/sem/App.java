package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

import com.napier.sem.Queries.CapitalCitiesQueries;
import com.napier.sem.Queries.CountriesQueries;
import com.napier.sem.Queries.LanguagesQuery;
import com.napier.sem.Queries.PopulationLivingQuery;

public class App {
    //Connection to MySQL database.
    private static Connection con = null;

    //Connect to the MySQL database.
    public Connection connect(String location) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
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
                System.out.println("Successfully connected");
                return DriverManager.getConnection("jdbc:mysql://" + location
                        + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
        return null;
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

    public Connection getCon(){
        return con;
    }

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        if(args.length < 1){
            con = a.connect("localhost:33060");
        }else{
            con = a.connect(args[0]);
        }

        // The population of people, people living in cities, and people not living in cities in each country
        ArrayList<Population> countryPopulations = PopulationLivingQuery.populationLivingReportCountry(con);
        PopulationLivingQuery.printPopulationLiving(countryPopulations, con);

        // The population of people, people living in cities, and people not living in cities in each continent
        ArrayList<Population> continentPopulations = PopulationLivingQuery.populationLivingReportContinent(con);
        PopulationLivingQuery.printPopulationLiving(continentPopulations, con);

        // The population of people, people living in cities, and people not living in cities in each region
        ArrayList<Population> regionPopulations = PopulationLivingQuery.populationLivingReportRegion(con);
        PopulationLivingQuery.printPopulationLiving(regionPopulations, con);

        LanguagesQuery.queryLanguage(con);
        CountriesQueries.getAllCountryReports(con);
        CapitalCitiesQueries.getAllCapitalCityReports(con);

        // Disconnect from database
        a.disconnect();
    }

}
