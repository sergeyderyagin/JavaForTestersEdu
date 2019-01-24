package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        // Создание контакта, если контакт отсутствует.
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("testname", "testlastname", "testnickname",
                    "testcompany", "testaddress", "79110001122", "+79110003344",
                    "+79110005566", "1@test.ru", "2@test.ru", "3@test.ru", "www.go.ru",
                    "test1"), true);
            app.getNavigationHelper().gotoHomePage();
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectionContact();
        app.getContactHelper().closeAlertAccept();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(before.size() - 1);

        Assert.assertEquals(after.size(), before.size() - 1);

    }
}
