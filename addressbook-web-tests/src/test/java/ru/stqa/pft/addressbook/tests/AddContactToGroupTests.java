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
        Contacts allContacts = app.contact().all();
        Groups allGroups = app.group().all();
        GroupData group;
        ContactData contact = getAvailableContact(allContacts, allGroups);

        if (contact != null) {
            group = getAvailableGroup(contact, allGroups);
        } else {
            app.goTo().groupPage();
            group = new GroupData().withName("test2name").withHeader("test2header").withFooter("test2footer");
            app.group().create(group);
            contact = app.contact().all().iterator().next();
        }

        int contactId = contact.getId();
        Groups groupsBefore = contact.getGroups();

        app.goTo().homePage();
        app.contact().addToGroup(contact, group);

        Groups groupsAfter = app.contact().getContactById(app.contact().all(), contactId).getGroups();

        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(group)));
    }

//    @Test(priority = 2)
//    public void testDeleteContactFromGroup(){
//        Groups before = new Groups();
//        for (ContactData contact : app.db().contacts()) {
//            if (contact.getId() == addingContact.getId()) {
//                before = contact.getGroups();
//                break;
//            }
//        }
//
//        app.goTo().homePage();
//        app.contact().deleteFromGroup(addingContact, newGroup);
//        Groups after = new Groups();
//
//        for (ContactData contact : app.db().contacts()) {
//            if (contact.getId() == addingContact.getId()) {
//                after = contact.getGroups();
//                break;
//            }
//        }
//        assertThat(after, equalTo(before.without(newGroup)));
//    }







    private ContactData getAvailableContact(Contacts allContacts, Groups allGroups) {
        for (ContactData contact : allContacts) {
            if (contact.getGroups().size() < allGroups.size()) {
                return contact;
            }
        }
        return null;
    }


    private GroupData getAvailableGroup(ContactData contact, Groups allGroups) {
        for (GroupData group : contact.getGroups()) {
            allGroups.remove(group);
        }
        assertTrue(allGroups.size() > 0);
        return allGroups.iterator().next();
    }

}
