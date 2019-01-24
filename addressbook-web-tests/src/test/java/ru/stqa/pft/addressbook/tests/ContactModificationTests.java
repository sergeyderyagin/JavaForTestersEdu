package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        // Создание контакта, если контакт отсутствует.
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("testfirstname", "testlastname", "testnickname",
                    "testcompany", "testaddress", "79110001122", "+79110003344",
                    "+79110005566", "1@test.ru", "2@test.ru", "3@test.ru", "www.go.ru",
                    "test1"), true);
            app.getNavigationHelper().gotoHomePage();
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification();
        ContactData editingContact = new ContactData(before.get(0).getId(), "edited_testfirstname", "edited_testlastname","edited_testnickname",
                "edited_testcompany", "edited_testaddress", "edited_79110001122", "edited_+79110003344",
                "edited_+79110005566", "edited_1@test.ru", "edited_2@test.ru", "edited_3@test.ru", "edited_www.go.ru",
                "test1");

        app.getContactHelper().fillContactForm(editingContact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(0);
        before.add(editingContact);

        Comparator<? super ContactData> comparatorById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(comparatorById);
        after.sort(comparatorById);

        Assert.assertEquals(before, after);

    }
}
