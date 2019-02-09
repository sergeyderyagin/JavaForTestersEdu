package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.UserData;

public class ResetPasswordHelper extends HelperBase{
    ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void loginViaUi (String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"),username);
        type(By.name("password"),password);
        click(By.cssSelector("input[type='submit']"));
        checkUserLogged(username);
    }

    public void goToManagePage(){
        wd.findElement(By.linkText("Manage")).click();
    }

    public void goToManageUsersPage(){
        wd.findElement(By.linkText("Manage Users")).click();
    }

    public void chooseUser(UserData user){
        click(By.linkText(user.getUsername()));
    }

    public String getUserName(){
        return wd.findElement(By.cssSelector("input[name='username']")).getAttribute("value");
    }

    public String getUserMail(){
        return wd.findElement(By.cssSelector("input[name='email']")).getAttribute("value");
    }

    public void resetPassword(){
        wd.findElement(By.cssSelector("input[value='Reset Password']")).click();
    }

    public void confirmChangePassword(String resetPasswordLink, String password){
        wd.get(resetPasswordLink);
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click(By.cssSelector("input[value='Update User']"));
    }

    private void checkUserLogged(String username) {
        WebElement element = wd.findElement(By.cssSelector("span[class='italic']"));
        element.getText().equals(username);
    }

}
