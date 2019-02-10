package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;


public class AddContactToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        // Создание группы, если группа отсутствует.
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2name").withHeader("test2header").withFooter("test2footer"));
        }

        // Создание контакта, если контакт отсутствует.
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("testfirstname2").withLastName("testlastname2")
                    .withAddress("testaddress")
                    .withHomePhone("79110001122").withMobilePhone("+79110003344").withWorkPhone("+79110005566")
                    .withEmail_1("1@test.ru").withEmail_2("2@test.ru").withEmail_3("3@test.ru"), true);
        }
    }

    @Test
    public void testAddContactToGroup() {
        GroupData group;
        ContactData contact = getAvailableContactToAddToGroup();
        int contactId = contact.getId();

        group = getAvailableGroupForAdd(contact);
        assertNotNull(group);

        Groups groupsBefore = contact.getGroups();

        app.goTo().homePage();
        app.contact().addToGroup(contact, group);

        Groups groupsAfter = app.contact().getContactById(app.db().contacts(), contactId).getGroups();

        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(group)));
    }

    @Test
    public void testDeleteContactFromGroup(){

        ContactData contact = getAvailableContactToDeleteFromGroup();
        int contactId = contact.getId();
        GroupData group = contact.getGroups().iterator().next();

        Groups groupsBefore = contact.getGroups();

        app.goTo().homePage();
        app.contact().deleteFromGroup(contact, group);

        Groups groupsAfter = app.contact().getContactById(app.db().contacts(), contactId).getGroups();


        assertThat(groupsAfter, equalTo(groupsBefore.without(group)));
    }







    private ContactData getAvailableContactToAddToGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() < groups.size()) {
                return contact;
            }
        }
        // Если подходяего контакта не нашлось, создаем новый
        app.goTo().homePage();
        ContactData contact = new ContactData().withFirstName("testfirstname2").withLastName("testlastname2")
                .withAddress("testaddress")
                .withHomePhone("79110001122").withMobilePhone("+79110003344").withWorkPhone("+79110005566")
                .withEmail_1("1@test.ru").withEmail_2("2@test.ru").withEmail_3("3@test.ru");
        app.contact().create(contact, true);
        return contact;
    }

    private GroupData getAvailableGroupForAdd(ContactData contact) {
        Groups groups = app.db().groups();
        for (GroupData group : contact.getGroups()) {
            groups.remove(group);
        }
        if (groups.size() > 0) {
            return groups.iterator().next();

        }
        return null;
    }


    private ContactData getAvailableContactToDeleteFromGroup() {
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() > 0) {
                return contact;
            }
        }
        // Если подходяего контакта не нашлось, добавляем этот контакт в группу
        ContactData contact = contacts.iterator().next();
        app.contact().addToGroup(contact, app.db().groups().iterator().next());
        return contact;
    }



}
