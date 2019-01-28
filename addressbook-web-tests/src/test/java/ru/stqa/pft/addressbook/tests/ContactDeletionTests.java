package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    // Создание контакта, если контакт отсутствует.
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("testfirstname2").withLastName("testlastname2"), true);
                    //.withNickName("testnickname").withCompany( "testcompany").withAddress("testaddress")
                    //.withHomePhoneNumber("79110001122").withMobilePhoneNumber("+79110003344").withWorkPhoneNumber("+79110005566")
                    //.withEmail_1("1@test.ru").withEmail_2("2@test.ru").withEmail_3("3@test.ru").withHomePage("www.go.ru").withGroup("test2name"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        ContactData deletingContact = before.iterator().next();

        app.contact().delete(deletingContact);
        app.goTo().homePage();

        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletingContact)));
    }

}
