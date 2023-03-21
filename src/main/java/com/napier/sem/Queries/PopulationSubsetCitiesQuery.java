package com.napier.sem.Queries;

import com.napier.sem.City;
import com.napier.sem.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PopulationSubsetCitiesQuery {
    //get city data
    public static void populationCitiesInSubset (Connection con){
        List<City> allCities = getAllCities(con);
        List<Country> allCountries = CountriesQueries.getAllCountries(con);

        //columns format
        String format = "%-50s %-10s %-40s %-15s";

        //report generation
        printReport(
                "All the cities in the world organised by largest population to smallest",
                format,
                allCities);

    }

    public static List<City> getAllCities(Connection con) {
        //create list to hold data
        List<City> allCities = new ArrayList<>();

        try
        {
            //create a SQL statement
            Statement stmt = con.createStatement();
            //execute SQL statement
            ResultSet rset = stmt.executeQuery(
                    "SELECT city.CountryCode AS 'CountryCode' " +
                            "city.Name AS 'Name' " +
                            "city.District AS 'District' " +
                            "city.Population AS 'Population' "
                            + "FROM city "
                            + "ORDER BY city.Population DESC ");

            while (rset.next())
            {
                //map query result to country object and add to list
                City city = new City();

                city.setCountryCode(rset.getString("CountryCode"));
                city.setName(rset.getString("Name"));
                city.setDistrict(rset.getString("District"));
                city.setPopulation(rset.getInt("Population"));

                allCities.add(city);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve city details");
        }

        return allCities;
    }

    //use list of all cities to get cities in a district
    public static List<City> getCitiesInDistrict(String district, List<City> cities) {
        List<City> citiesInDistrict = new ArrayList<>();

        for (City city:cities) {
            if (city.getDistrict().equals(district)) {
                citiesInDistrict.add(city);
            }
        }

        return citiesInDistrict;
    }
    //use list of all cities/countries to get cities in a country
    public static List<City> getCitiesInCountry(String country, List<City> cities, List<Country> countries) {
        List<City> citiesInCountry = new ArrayList<>();
        String countryCode = "";
        for (Country c:countries){
            if (c.getName().equals(country)){
                countryCode = c.getCode();
            }
        }

        for (City city:cities) {
            if (city.getCountryCode().equals(countryCode)) {
                citiesInCountry.add(city);
            }
        }

        return citiesInCountry;
    }
    //use list of all cities/countries to get cities in a region
    public static List<City> getCitiesInRegion(String region, List<City> cities, List<Country> countries) {
        List<City> citiesInRegion = new ArrayList<>();
        List<Country> countriesInRegion = CountriesQueries.getCountriesInRegion(region, countries);
        List<String> countryCodes = new ArrayList<>();

        for (Country c:countriesInRegion){
            countryCodes.add(c.getCode());
        }

        for (City city:cities) {
            for (String code: countryCodes) {
                if (city.getCountryCode().equals(code)) {
                    citiesInRegion.add(city);
                }
            }
        }

        return citiesInRegion;
    }
    //use list of all cities/countries to get cities in a region
    public static List<City> getCitiesInContinent(String continent, List<City> cities, List<Country> countries) {
        List<City> citiesInContinent = new ArrayList<>();
        List<Country> countriesInContinent = CountriesQueries.getCountriesInContinent(continent, countries);
        List<String> countryCodes = new ArrayList<>();

        for (Country c:countriesInContinent){
            countryCodes.add(c.getCode());
        }

        for (City city:cities) {
            for (String code: countryCodes) {
                if (city.getCountryCode().equals(code)) {
                    citiesInContinent.add(city);
                }
            }
        }

        return citiesInContinent;
    }

    //method to print a report from a list
    public static void printReport(String header, String format, List<City> list) {
        System.out.println(String.format(header));

        System.out.println(String.format(format,
                "Name", "Country", "District", "Population"));

        // Loop over all countries in the list
        for (City city : list)
        {
            if (city == null) {
                System.out.println("City is null");
                continue;
            }

            System.out.println(String.format(format,
                    city.getName(),
                    city.getCountryCode(),
                    city.getDistrict(),
                    city.getPopulation()));
        }
    }
}

