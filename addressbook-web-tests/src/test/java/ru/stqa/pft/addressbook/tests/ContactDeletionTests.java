package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    // Создание контакта, если контакт отсутствует.
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("testfirstname2").withLastName("testlastname2")
                    .withAddress("testaddress")
                    .withHomePhone("79110001122").withMobilePhone("+79110003344").withWorkPhone("+79110005566")
                    .withEmail_1("1@test.ru").withEmail_2("2@test.ru").withEmail_3("3@test.ru"), true);
        }
    }

    @Test
    public void testContactDeletion() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData deletingContact = before.iterator().next();

        app.contact().delete(deletingContact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() - 1));

        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletingContact)));
    }

}
