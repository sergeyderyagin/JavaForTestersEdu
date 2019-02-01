package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

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
    public void testContactModification() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData editingContact = before.iterator().next();

        ContactData editedContact = new ContactData()
                .withId(editingContact.getId()).withFirstName("edited_testfirstname2").withLastName("edited_testlastname2")
                .withAddress("edited_testaddress")
                .withHomePhone("edited_111").withMobilePhone("edited_222").withWorkPhone("edited_333")
                .withEmail_1("edited_1@test.ru").withEmail_2("edited_2@test.ru").withEmail_3("edited_3@test.ru")
                .withGroup("test2name");
        app.goTo().homePage();

        app.contact().modify(editedContact);
        app.goTo().homePage();
//        assertThat(app.contact().count(), equalTo(before.size()));

        Contacts after = app.db().contacts();
        before.remove(editingContact);
        before.add(editedContact);
        assertThat(after, equalTo(before.without(editingContact).withAdded(editedContact)));
    }

}