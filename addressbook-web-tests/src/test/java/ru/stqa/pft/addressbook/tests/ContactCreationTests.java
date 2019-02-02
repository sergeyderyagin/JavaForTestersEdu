package ru.stqa.pft.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase{

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        String json = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); // List<ContactData>.class
        return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }


    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData newContact) {
        app.goTo().homePage();
        Contacts before = app.db().contacts();

        app.contact().create(newContact, true);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));

        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }


}
