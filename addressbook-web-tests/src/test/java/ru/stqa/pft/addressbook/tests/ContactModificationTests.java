package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    /**
     * Создание контакта, если контакт отсутствует.
     */
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstName("testfirstname2").withLastName("testlastname2"), true);
                    //.withCompany( "testcompany").withAddress("testaddress")
                    //.withHomePhoneNumber("79110001122").withMobilePhoneNumber("+79110003344").withWorkPhoneNumber("+79110005566")
                    //.withEmail_1("1@test.ru").withEmail_2("2@test.ru").withEmail_3("3@test.ru").withHomePage("www.go.ru").withGroup("test2name"), true);
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData editableContact = before.iterator().next();

        ContactData editedContact = new ContactData().withId(editableContact.getId()).withFirstName("edited_testfirstname2").withLastName("edited_testlastname2");
                //.withNickName("edited_testnickname");//.withCompany( "edited_testcompany").withAddress("edited_testaddress")
                //.withHomePhoneNumber("edited_79110001122").withMobilePhoneNumber("edited_+79110003344").withWorkPhoneNumber("edited_+79110005566")
                //.withEmail_1("edited_1@test.ru").withEmail_2("edited_2@test.ru").withEmail_3("edited_3@test.ru").withHomePage("edited_www.go.ru").withGroup("test2name");
        app.goTo().homePage();

        app.contact().modify(editedContact);
        app.goTo().homePage();

        Set<ContactData> after = app.contact().all();

        Assert.assertEquals(after.size(), before.size());

        before.remove(editableContact);
        before.add(editedContact);
        Assert.assertEquals(before, after);
        int i = 2345;
    }

}