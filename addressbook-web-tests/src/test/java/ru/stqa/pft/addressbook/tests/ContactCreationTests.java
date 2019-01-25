package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;


public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();

        ContactData newContact = new ContactData().withFirstName("testfirstname2").withLastName("testlastname2").withGroup("test2name");
                //.withCompany( "testcompany").withAddress("testaddress")
                //.withHomePhoneNumber("79110001122").withMobilePhoneNumber("+79110003344").withWorkPhoneNumber("+79110005566")
                //.withEmail_1("1@test.ru").withEmail_2("2@test.ru").withEmail_3("3@test.ru").withHomePage("www.go.ru").withGroup("test2name");

        app.contact().create(newContact, true);
        app.goTo().homePage();

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());

        before.add(newContact);
        Assert.assertEquals(before, after);
    }



}
