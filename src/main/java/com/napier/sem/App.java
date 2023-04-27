package com.napier.sem;

import com.napier.sem.Queries.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public Connection connect(String location) {
        try {
            /**
             * Load Database driver
             */
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }
        /**
         * connection to the DB
         */
        /**
         *setting a counter to decrease
         */
        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                /**
                 * Wait a bit for db to start
                 */
                Thread.sleep(30000);
                /**
                 * Connect to database
                 */
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

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                /**
                 * Close connection
                 */
                con.close();
            } catch (Exception e) /**
             * Catching an exception
             */ {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public static void main(String[] args) {
        /**
         * Create new Application
         */
        App a = new App();
        /**
         * Connect to database
         */
        if (args.length < 1) {
            con = a.connect("localhost:33060");
        } else {
            con = a.connect(args[0]);
        }

        /**
         * mocks a user input for "limit by N" queries
         * mocks a user input for "region"
         * mocks a user input for "continent", etc
         * in the real app, these would simply be integrated with a front end input for the user
         */
        int limitBy = 3;
        String continent = "Europe";
        String region = "Eastern Asia";
        String country = "Germany";
        String district = "England";
        String city = "London";

        /**
         * mock check "n not less than 1"
         * but should do this check on the front end form for fast fail
         */
        if (limitBy < 1) {
            throw new NullPointerException("N must be an integer greater than 0");
        }

        /**
         * Calls queries from respective files
         */
        CapitalCitiesQueries.getAllCapitalCityReports(con, limitBy, region, continent);
        CountriesQueries.getAllCountryReports(con, limitBy, region, continent);
        LanguagesQuery.LanguagesReport(con);
        PopulationLivingQuery.populationLivingReportQuery(con);
        PopulationSubsetCitiesQuery.populationCitiesInSubset(con, limitBy, region, continent, country, district);
        TotalPopulationQueries.getTotalPopulations(con, region, continent, country, district, city);

        /**
         * Disconnect from database
         */
        a.disconnect();
    }

}
