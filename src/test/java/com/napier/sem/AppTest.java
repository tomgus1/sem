package com.napier.sem;

import com.napier.sem.Queries.PopulationLivingQuery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class AppTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();

    }

    @Test
    void printPopulationLivingTestNull()
    {
        PopulationLivingQuery.printPopulationLiving(null, app.getCon());
    }
}
