package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("testname_edited", "testlastname_edited",
                "testnickname_edited", "testcompany_edited", "virtual address_edited",
                "79110001122_edited", "+79110003344_edited", "+79110005566_edited",
                "1@test.ru_edited", "2@test.ru_edited", "3@test.ru_edited", "www.go.ru_edited",
                null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}
