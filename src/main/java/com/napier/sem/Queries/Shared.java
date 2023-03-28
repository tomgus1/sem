package com.napier.sem.Queries;

import java.sql.Connection;
import java.sql.Statement;

public class Shared {
    public static Statement CreateStatement(Connection con) {
        Statement statement = null;
        try {
            statement = con.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to create SQL statement");
        }
        return statement;
    }
}
