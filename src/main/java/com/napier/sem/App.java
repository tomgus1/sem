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
             */
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public static Connection getCon(){
        return con;
    }

    public static void main(String[] args) {
        /**
         * Create new Application
         */
        App a = new App();
        /**
         * Connect to database
         */
        if(args.length < 1){
            con = a.connect("localhost:33060");
        }else{
            con = a.connect(args[0]);
        }
        /**
        * Calls queries from respective files
         * mocks a user input for "limit by N" queries
        */
        int limitBy = 3;
        CapitalCitiesQueries.getAllCapitalCityReports(con);
        CountriesQueries.getAllCountryReports(con, limitBy);
        LanguagesQuery.LanguagesReport(con);
        PopulationLivingQuery.populationLivingReportQuery(con);
        PopulationSubsetCitiesQuery.populationCitiesInSubset(con);
        TotalPopulationQueries.getTotalPopulations(con);

        /**
         * Disconnect from database
         */
        a.disconnect();
    }

}
