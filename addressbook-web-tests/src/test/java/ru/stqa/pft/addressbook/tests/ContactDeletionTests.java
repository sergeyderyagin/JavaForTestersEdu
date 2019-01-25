package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

    /**
     * Создание контакта, если контакт отсутствует.
     */
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstName("testfirstname2").withLastName("testlastname2"), true);
                    //.withNickName("testnickname").withCompany( "testcompany").withAddress("testaddress")
                    //.withHomePhoneNumber("79110001122").withMobilePhoneNumber("+79110003344").withWorkPhoneNumber("+79110005566")
                    //.withEmail_1("1@test.ru").withEmail_2("2@test.ru").withEmail_3("3@test.ru").withHomePage("www.go.ru").withGroup("test2name"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion() {
        Set<ContactData> before = app.contact().all();
        ContactData deletingContact = before.iterator().next();

        app.contact().delete(deletingContact);
        app.goTo().homePage();

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletingContact);
        Assert.assertEquals(before, after);

    }

}
