package com.napier.sem.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TotalPopulationQueries {

    public static void getPopulationWorld (Connection con){
        long population = 0;
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(
                    "SELECT SUM(Population) AS Population "
                            + "FROM country");

            while (rset.next())
            {
                population = rset.getLong("Population");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population details");
        }
        System.out.println("World Population: " + population);
    }
    public static void getPopulationContinent (String name, Connection con){
        long population = 0;
        try
        {
            // Create an SQL statement
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT Continent, SUM(Population) AS Population "
                            + "FROM country "
                            + "WHERE Continent LIKE ? "
                            + "GROUP BY Continent");
            stmt.setString(1, name);
            // Execute SQL statement

            ResultSet rset = stmt.executeQuery();

            while (rset.next())
            {
                population = rset.getLong("Population");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population details");
        }
        System.out.println("Population of " + name + ": "+ population);
    }
    public static void getPopulationRegion (String name, Connection con){
        long population = 0;
        try
        {
            // Create an SQL statement
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT Region, SUM(Population) AS Population "
                            + "FROM country "
                            + "WHERE Region LIKE ? "
                            + "GROUP BY Region");
            stmt.setString(1, name);
            // Execute SQL statement

            ResultSet rset = stmt.executeQuery();

            while (rset.next())
            {
                population = rset.getLong("Population");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population details");
        }
        System.out.println("Population of " + name + ": "+ population);
    }
    public static void getPopulationCountry (String name, Connection con){
        long population = 0;
        try
        {
            // Create an SQL statement
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT Population "
                            + "FROM country "
                            + "WHERE Name LIKE ?");
            stmt.setString(1, name);
            // Execute SQL statement

            ResultSet rset = stmt.executeQuery();

            while (rset.next())
            {
                population = rset.getLong("Population");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population details");
        }
        System.out.println("Population of " + name + ": "+ population);
    }
    public static void getPopulationDistrict (String name, Connection con){
        long population = 0;
        try
        {
            // Create an SQL statement
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT District, SUM(Population) AS Population "
                            + "FROM city "
                            + "WHERE District LIKE ? "
                            + "GROUP BY District");
            stmt.setString(1, name);
            // Execute SQL statement

            ResultSet rset = stmt.executeQuery();

            while (rset.next())
            {
                population = rset.getLong("Population");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population details");
        }
        System.out.println("Population of " + name + ": "+ population);
    }
    public static void getPopulationCity (String name, Connection con){
        long population = 0;
        try
        {
            // Create an SQL statement
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT Population AS Population "
                            + "FROM city "
                            + "WHERE Name LIKE ? ");
            stmt.setString(1, name);
            // Execute SQL statement

            ResultSet rset = stmt.executeQuery();

            while (rset.next())
            {
                population = rset.getLong("Population");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population details");
        }
        System.out.println("Population of " + name + ": "+ population);
    }
}
