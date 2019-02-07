package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetUserPassword() throws IOException, MessagingException {
        app.resetPassword().loginViaUi(app.getProperty("web.user"), app.getProperty("web.pass"));
        app.resetPassword().goToManagePage();
        app.resetPassword().goToManageUsersPage();
        app.resetPassword().chooseUser();
        String user = String.valueOf(app.resetPassword().getUserName());
        String email = String.valueOf(app.resetPassword().getUserMail());
        String password = "password";
        System.out.println(user);
        System.out.println(email);

        app.resetPassword().resetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
//        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationLink = app.james().findConfirmationLink(mailMessages, email);

        app.resetPassword().confirmChangePassword(confirmationLink, password);

        HttpSession session = app.newSession();
        assertTrue(session.login(user, password));
        assertTrue(session.isLoggedInAs(user));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
