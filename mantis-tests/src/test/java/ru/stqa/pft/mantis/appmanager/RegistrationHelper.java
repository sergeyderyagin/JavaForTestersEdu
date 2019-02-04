package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    RegistrationHelper(ApplicationManager app) {
        super(app);
    }


    public void start(String user, String email) {
        wd.get(app.getProperty("web.url") + "/signup_page.php");
        type(By.name("username"), user);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}