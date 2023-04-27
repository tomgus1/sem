package com.napier.sem.Queries;

import com.napier.sem.Language;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LanguagesQuery {
    /**
     * Producing Languages report
     */
    public static void LanguagesReport(Connection con) {
        /**
         * Holds a list of results
         */
        List<Language> speakersReport = getLanguages(con);
        /**
         *Setting string format
         */
        String format =  "%-10s %-15s %-20s";
        /**
         * Prints the report
         */
        printReport("Languages Spoken by top 5", format, speakersReport);
    }

    public static List<Language> getLanguages(Connection con) {
        /**
         * Holds a list of queried results
         */
        List<Language> languages = new ArrayList<>();

        try {
            /**
             * Create an SQL statement
             */
            Statement stmt = Shared.CreateStatement(con);
            /**
             * Execute SQL statement
             */
            ResultSet rset = stmt.executeQuery(
                    "SELECT countrylanguage.Language AS 'Language', " +
                            "(SUM((country.Population * countrylanguage.Percentage) / 100)) AS 'Population', " +
                            "(((SUM((country.Population * countrylanguage.Percentage) / 100)) * 100) / (SELECT SUM(country.Population) FROM country)) AS 'PercentageGlobal' "
                            + "FROM countrylanguage, country "
                            + "WHERE (countrylanguage.Language = 'Chinese' "
                            + "OR countrylanguage.Language = 'English' "
                            + "OR countrylanguage.Language = 'Hindi' "
                            + "OR countrylanguage.Language = 'Spanish' "
                            + "OR countrylanguage.Language = 'Arabic') "
                            + "AND countrylanguage.CountryCode = country.Code "
                            + "GROUP BY countrylanguage.Language "
                            + "ORDER BY Population DESC ");

            while (rset.next()) {
                /**
                 * Define population
                 */
                Language language = new Language();

                /**
                 * Extract data from SQL query result
                 */
                language.setLanguage(rset.getString("Language"));
                language.setPopulation(rset.getLong("Population"));
                language.setPercentage(rset.getFloat("PercentageGlobal"));

                /**
                 * Add population to list
                 */
                languages.add(language);
            }
        }
        /**
         * catching an exeption
         */
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve population details");
        }
        /**
         * return languages
         */
        return languages;
    }

    public static void printReport(String header, String format, List<Language> list) {
        System.out.println(String.format(header));
        /**
         * Print header
         */
        System.out.println(String.format(format,
                "Language", "Speakers", "% Worldwide Speakers"));

        /**
         * Loop over all languages in the list
         */
        for (Language language : list)
        {
            if (language == null)
                continue;
            /**
             * print the report
             */
            System.out.println(String.format(format,
                    language.getLanguage(), language.getPopulation(), language.getPercentage()));
        }
    }
}
