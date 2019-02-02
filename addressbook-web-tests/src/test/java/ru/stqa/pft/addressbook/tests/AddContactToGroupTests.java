package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddContactToGroupTests extends TestBase {
    private ContactData addingContact;
    private GroupData newGroup = new GroupData().withName("test2name").withHeader("test2header").withFooter("test2footer");

    // Создание контакта, если контакт отсутствует.
    @BeforeMethod(enabled = false)
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("testfirstname2").withLastName("testlastname2")
                    .withAddress("testaddress")
                    .withHomePhone("79110001122").withMobilePhone("+79110003344").withWorkPhone("+79110005566")
                    .withEmail_1("1@test.ru").withEmail_2("2@test.ru").withEmail_3("3@test.ru"), true);
        }
    }

    @Test(priority = 1)
    public void testAddContactToGroup() {
        app.goTo().groupPage();
        app.group().create(newGroup);
        Groups after = app.db().groups();

        app.goTo().homePage();
        addingContact = app.db().contacts().iterator().next();
        Groups groupsBefore = addingContact.getGroups();

        newGroup = newGroup.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());

        app.contact().addToGroup(addingContact, newGroup);
        Groups groupsAfter = new Groups();
        for (ContactData contact : app.db().contacts()) {
            if (contact.getId() == addingContact.getId()) {
                groupsAfter = contact.getGroups();
                break;
            }
        }

        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(newGroup)));
    }

    @Test(priority = 2)
    public void testDeleteContactFromGroup(){
        Groups before = new Groups();
        for (ContactData contact : app.db().contacts()) {
            if (contact.getId() == addingContact.getId()) {
                before = contact.getGroups();
                break;
            }
        }

        app.goTo().homePage();
        app.contact().deleteFromGroup(addingContact, newGroup);
        Groups after = new Groups();

        for (ContactData contact : app.db().contacts()) {
            if (contact.getId() == addingContact.getId()) {
                after = contact.getGroups();
                break;
            }
        }
        assertThat(after, equalTo(before.without(newGroup)));
    }

}
