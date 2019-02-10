package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactHelper extends HelperBase {
    private Contacts contactsCache = null;

    ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void create(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactForm(contact, creation);
        submitContactCreation();
        contactsCache = null;
    }

    public void modify(ContactData contact) {
        selectById(contact.getId());
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactsCache = null;
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        click(By.xpath("//input[@value='Delete']"));
        closeAlertAccept();
        contactsCache = null;
    }

    public void addToGroup(ContactData contact, GroupData group) {
        selectById(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
        click(By.name("add"));
        contactsCache = null;
    }

    public void deleteFromGroup(ContactData contact, GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(group.getId()));
        selectById(contact.getId());
        click(By.name("remove"));
        contactsCache = null;
    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contact, boolean creation) {
        type(By.name("firstname"), contact.getFirstName());
        type(By.name("lastname"), contact.getLastName());
        type(By.name("address"), contact.getAddress());
        type(By.name("home"), contact.getHomePhone());
        type(By.name("mobile"), contact.getMobilePhone());
        type(By.name("work"), contact.getWorkPhone());
        type(By.name("email"), contact.getEmail1());
        type(By.name("email2"), contact.getEmail2());
        type(By.name("email3"), contact.getEmail3());
//        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contact.getGroups().size() > 0) {
                assertEquals(contact.getGroups().size(), 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroups().iterator().next().getName());

            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }


    public void initContactModificationById(int id) {
        wd.findElement(By.xpath("//td/a[contains(@href, 'edit') and contains(@href, 'id=" + id +"')]")).click();
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


    public Contacts all() {
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));

        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.cssSelector("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            contactsCache.add(new ContactData()
                    .withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails));
        }
        return new Contacts(contactsCache);
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail_1(email1).withEmail_2(email2).withEmail_3(email3);
    }

    public ContactData getContactById(Contacts contacts, int id) {
        for(ContactData contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }
}
