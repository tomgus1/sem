package com.napier.sem.Queries;

import com.napier.sem.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CountriesQueries {

    //todo, add user input for continent, region
    public static void getAllCountryReports(Connection con) {
        //get country data
        ArrayList<Country> allCountries = getAllCountries(con);
        ArrayList<Country> countriesInContinent = getCountriesInContinent("Europe", allCountries);
        ArrayList<Country> countriesInRegion = getCountriesInRegion("Eastern Asia", allCountries);

        //columns format
        String format = "%-10s %-50s %-20s %-40s %-15s %-15s";

        //report generation
        printReport(
                "All the countries in the world organised by largest population to smallest",
                format,
                allCountries);
        printReport(
                "All the countries in a continent organised by largest population to smallest.",
                format,
                countriesInContinent
        );
        printReport(
                "All the countries in a region organised by largest population to smallest.",
                format,
                countriesInRegion);
    }

    public static ArrayList<Country> getAllCountries(Connection con) {
        //create list to hold data
        ArrayList<Country> allCountries = new ArrayList<>();

        try
        {
            //create a SQL statement
            Statement stmt = con.createStatement();
            //execute SQL statement
            ResultSet rset = stmt.executeQuery(
                    "SELECT country.Code AS 'Code'," +
                            "country.Name AS 'Name'," +
                            "country.Continent AS 'Continent'," +
                            "country.Region AS 'Region'," +
                            "country.Population AS 'Population'," +
                            "country.Capital AS 'Capital'"
                            + "FROM country "
                            + "GROUP BY country.Code "
                            + "ORDER BY country.Population DESC ");

            while (rset.next())
            {
                //map query result to country object and add to list
                Country country = new Country();

                country.setCode(rset.getString("Code"));
                country.setName(rset.getString("Name"));
                country.setContinent(rset.getString("Continent"));
                country.setRegion(rset.getString("Region"));
                country.setPopulation(rset.getInt("Population"));
                country.setCapital(rset.getString("Capital"));

                allCountries.add(country);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve country details");
        }

        return allCountries;
    }

    //use list of all countries to get countries in a continent
    public static ArrayList<Country> getCountriesInContinent(String continent, ArrayList<Country> countries) {
        ArrayList<Country> countriesInContinent = new ArrayList<>();

        for (Country country:countries) {
            if (country.getContinent().equals(continent)) {
                countriesInContinent.add(country);
            }
        }

        return countriesInContinent;
    }

    //use list of all countries to get countries in a region
    public static ArrayList<Country> getCountriesInRegion(String region, ArrayList<Country> countries) {
        ArrayList<Country> countriesInRegion = new ArrayList<>();

        for (Country country:countries) {
            if (country.getRegion().equals(region)) {
                countriesInRegion.add(country);
            }
        }

        return countriesInRegion;
    }

    //method to print a report from a list
    public static void printReport(String header, String format, ArrayList<Country> list) {
        System.out.println(String.format(header));

        System.out.println(String.format(format,
                "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Loop over all countries in the list
        for (Country country : list)
        {
            if (country == null) {
                System.out.println("Country is null");
                continue;
            }

            System.out.println(String.format(format,
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital()));
        }
    }
}
