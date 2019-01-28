package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();

        ContactData newContact = new ContactData().withFirstName("testfirstname2").withLastName("testlastname2").withGroup("test2name");
                //.withCompany( "testcompany").withAddress("testaddress")
                //.withHomePhoneNumber("79110001122").withMobilePhoneNumber("+79110003344").withWorkPhoneNumber("+79110005566")
                //.withEmail_1("1@test.ru").withEmail_2("2@test.ru").withEmail_3("3@test.ru").withHomePage("www.go.ru").withGroup("test2name");

        app.contact().create(newContact, true);
        app.goTo().homePage();

        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
