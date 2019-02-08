package ru.stqa.pft.mantis.tests;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

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

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {

        String resolution = app.soap().getResolution(issueId);
        return !resolution.equals("fixed");
    }

}