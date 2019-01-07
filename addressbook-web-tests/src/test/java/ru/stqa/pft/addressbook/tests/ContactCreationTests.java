package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("testname", "testlastname", "testnickname",
                "testcompany", "virtual address", "79110001122", "+79110003344",
                "+79110005566", "1@test.ru", "2@test.ru", "3@test.ru", "www.go.ru",
                "test1"), true);
        app.getContactHelper().submitContactCreation();
    }

}
