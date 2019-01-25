package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase {
    private Comparator<? super ContactData> comparatorById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

    /**
     * Создание контакта, если контакт отсутствует.
     */
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.contact().create(new ContactData("testfirstname", "testlastname", "testnickname",
                    "testcompany", "testaddress", "79110001122", "+79110003344",
                    "+79110005566", "1@test.ru", "2@test.ru", "3@test.ru", "www.go.ru",
                    "test1"), true);
            app.goTo().homePage();
        }
    }

    @Test(enabled = false)
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;

        app.contact().deleteContact(index);
        app.goTo().homePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        before.sort(comparatorById);
        after.sort(comparatorById);
        Assert.assertEquals(before, after);

    }

}
