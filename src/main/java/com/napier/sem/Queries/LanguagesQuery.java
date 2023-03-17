package com.napier.sem.Queries;

import com.napier.sem.Language;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LanguagesQuery {

    public static void queryLanguage(Connection con)
    {
        // Holds a list of queried results
        ArrayList<Language> languages = new ArrayList<Language>();
//        let Connection con=con;

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
}
