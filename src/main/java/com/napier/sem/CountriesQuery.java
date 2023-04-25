//package com.napier.sem;
//
//import com.napier.sem.Queries.Shared;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CountriesQuery {
//
//    public static class CountryDataSource {
//        private Connection connection;
//
//        public CountryDataSource(Connection connection) {
//            this.connection = connection;
//        }
//
//        private ResultSet getSqlResults(Statement stmt, String query) {
//            //create a SQL statement
//            ResultSet rset = null;
//
//            try {
//                //execute SQL statement
//                rset = stmt.executeQuery(query);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                System.out.println("Failed to retrieve query details from database");
//            }
//
//            return rset;
//        }
//
//        private List<Country> setResultToCountryList(ResultSet rset) {
//            List<Country> countries = new ArrayList<>();
//
//            try {
//                while (rset.next()) {
//                    //map query result to country object and add to list
//                    Country country = new Country();
//
//                    country.setCode(rset.getString("Code"));
//                    country.setName(rset.getString("Name"));
//                    country.setContinent(rset.getString("Continent"));
//                    country.setRegion(rset.getString("Region"));
//                    country.setPopulation(rset.getInt("Population"));
//                    country.setCapital(rset.getString("Capital"));
//
//                    countries.add(country);
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                System.out.println("Failed to set country details to list");
//            }
//
//            return countries;
//        }
//
//        public List<Country> getCountries() {
//            Statement statement = Shared.CreateStatement(connection);
//            String query = "SELECT country.Code AS 'Code'," +
//                    "country.Name AS 'Name'," +
//                    "country.Continent AS 'Continent'," +
//                    "country.Region AS 'Region'," +
//                    "country.Population AS 'Population'," +
//                    "country.Capital AS 'Capital' " +
//                    "FROM country " +
//                    "GROUP BY country.Code " +
//                    "ORDER BY country.Population DESC ";
//
//            ResultSet rset = getSqlResults(statement, query);
//
//            return setResultToCountryList(rset);
//        }
//    }
//
//    public class CountryProcessor {
//        public List<Country> getCountriesInContinent(String continent, List<Country> countries) {
//            List<Country> countriesInContinent = new ArrayList<>();
//
//            for (Country country : countries) {
//                if (country.getContinent().equals(continent)) {
//                    countriesInContinent.add(country);
//                }
//            }
//
//            return countriesInContinent;
//        }
//
//        public List<Country> getCountriesInRegion(String region, List<Country> countries) {
//            List<Country> countriesInRegion = new ArrayList<>();
//
//            for (Country country : countries) {
//                if (country.getRegion().equals(region)) {
//                    countriesInRegion.add(country);
//                }
//            }
//
//            return countriesInRegion;
//        }
//
//        public List<Country> getCountriesLimitedBy(int limit, List<Country> countries) {
//            List<Country> countriesLimited = new ArrayList<>();
//
//            if (limit > countries.size()) {
//                limit = countries.size();
//            }
//
//            for (int i = 0; i < limit; i++) {
//                countriesLimited.add(countries.get(i));
//            }
//
//            return countriesLimited;
//        }
//    }
//
//    public class ReportPrinter {
//        private List<Country> countries;
//        private String header;
//        private String format;
//
//        public void printAllCountriesReport(List<Country> countries, String header, String format) {
//            this.countries = countries;
//            this.header = header;
//            this.format = format;
//            printReport();
//        }
//
//        private void printReport() {
//            System.out.println(String.format(header));
//
//            System.out.println(String.format(format,
//                    "Code", "Name", "Continent", "Region", "Population", "Capital"));
//
//            // Loop over all countries in the list
//            for (Country country : countries) {
//                if (country == null) {
//                    System.out.println("Country is null");
//                    continue;
//                }
//
//                System.out.println(String.format(format,
//                        country.getCode(),
//                        country.getName(),
//                        country.getContinent(),
//                        country.getRegion(),
//                        country.getPopulation(),
//                        country.getCapital()));
//            }
//        }
//    }
//
//    public class AllReportsCreator {
//        public AllReportsCreator(CountryDataSource dataSource, CountryProcessor processor, ReportPrinter printer) {
//
//        }
//
//        public void createReport() {
//            List<Country> allCountries = getAllCountries(con);
//            List<Country> countriesInContinent = getCountriesInContinent("Europe", allCountries);
//            List<Country> countriesInRegion = getCountriesInRegion("Eastern Asia", allCountries);
//            List<Country> allCountriesLimited = getCountriesLimitedBy(3, allCountries);
//            List<Country> countriesInContinentLimited = getCountriesLimitedBy(3, countriesInContinent);
//            List<Country> countriesInRegionLimited = getCountriesLimitedBy(3, countriesInRegion);
//        }
//    }
//}
