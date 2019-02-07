package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;


public class TestBase {
    final static ApplicationManager app = new ApplicationManager();

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
//        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.stop();
//        app.ftp().restore("config_inc.php.bak", "config_inc.php");
    }

}