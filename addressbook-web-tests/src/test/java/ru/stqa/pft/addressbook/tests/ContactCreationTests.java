package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactCreation();
        ContactData newContact = new ContactData("testfirstname2", "testlastname2", "testnickname",
                "testcompany", "testaddress", "79110001122", "+79110003344",
                "+79110005566", "1@test.ru", "2@test.ru", "3@test.ru", "www.go.ru",
                "test1");
        app.getContactHelper().fillContactForm(newContact, true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(newContact);

        Comparator<? super ContactData> comparatorById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(comparatorById);
        after.sort(comparatorById);

        Assert.assertEquals(before, after);
    }

}
