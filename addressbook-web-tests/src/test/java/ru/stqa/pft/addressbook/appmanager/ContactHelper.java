package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
    ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void create(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactForm(contact, creation);
        submitContactCreation();
    }

    public void modify(ContactData contact) {
        selectById(contact.getId());
        initContactModification(contact);
        fillContactForm(contact, false);
        submitContactModification();
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        click(By.xpath("//input[@value='Delete']"));
        closeAlertAccept();
    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
//        type(By.name("nickname"), contactData.getNickName());
//        type(By.name("company"), contactData.getCompany());
//        type(By.name("address"), contactData.getAddress());
//        type(By.name("home"), contactData.getHomePhoneNumber());
//        type(By.name("mobile"), contactData.getMobilePhoneNumber());
//        type(By.name("work"), contactData.getWorkPhoneNumber());
//        type(By.name("email"), contactData.getEmail1());
//        type(By.name("email2"), contactData.getEmail2());
//        type(By.name("email3"), contactData.getEmail3());
//        type(By.name("homepage"), contactData.getHomePage());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }


    public void initContactModification(ContactData contact) {
        WebElement editButton = wd.findElement(By.xpath("//td/a[contains(@href, 'edit') and contains(@href, 'id=" + contact.getId() +"')]"));
        editButton.click();
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }


    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }


    public void closeAlertAccept() {
        wd.switchTo().alert().accept();
    }


    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }


    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));

        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.cssSelector("td"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }

}
