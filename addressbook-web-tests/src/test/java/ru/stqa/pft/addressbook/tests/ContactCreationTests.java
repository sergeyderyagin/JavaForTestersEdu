package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{
    private Comparator<? super ContactData> comparatorById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

    @Test(enabled = false)
    public void testContactCreation() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();

        ContactData newContact = new ContactData("testfirstname2", "testlastname2", "testnickname",
                "testcompany", "testaddress", "79110001122", "+79110003344",
                "+79110005566", "1@test.ru", "2@test.ru", "3@test.ru", "www.go.ru",
                "test1");
        app.contact().create(newContact, true);
        app.goTo().homePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(newContact);
        before.sort(comparatorById);
        after.sort(comparatorById);
        Assert.assertEquals(before, after);
    }



}
