package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();

        File photo = new File("src/test/resources/img.jpg");
        ContactData newContact = new ContactData()
                .withFirstName("testfirstname2").withLastName("testlastname2").withAddress("testaddress")
                .withHomePhone("79110001122").withMobilePhone("+79110003344").withWorkPhone("+79110005566")
                .withEmail_1("1@test.ru").withEmail_2("2@test.ru").withEmail_3("3@test.ru")
                .withGroup("test2name").withPhoto(photo);

        app.contact().create(newContact, true);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));

        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}
