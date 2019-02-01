package ru.stqa.pft.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {
    Logger LOGGER = LoggerFactory.getLogger(TestBase.class);
    final static ApplicationManager app = new ApplicationManager();

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }


    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m, Object[] p) {
        LOGGER.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));

    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        LOGGER.info("Stop test " + m.getName());

    }

}
