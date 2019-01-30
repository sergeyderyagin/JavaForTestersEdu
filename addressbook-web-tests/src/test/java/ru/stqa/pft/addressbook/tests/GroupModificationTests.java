package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    // Создание контакта, если контакт отсутствует.
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test2name").withHeader("test2header").withFooter("test2footer"));
            app.goTo().groupPage();
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.group().all();

        GroupData editingGroup = before.iterator().next();

        GroupData editedGroup = new GroupData()
                .withId(editingGroup.getId()).withName("test2name").withHeader("test2header").withFooter("test2footer");

        app.group().modify(editedGroup);
        app.goTo().groupPage();
        assertThat(app.group().count(), equalTo(before.size()));

        Set<GroupData> after = app.group().all();
        before.remove(editingGroup);
        before.add(editedGroup);
        assertThat(after, equalTo(before.without(editingGroup).withAdded(editedGroup)));

    }

}
