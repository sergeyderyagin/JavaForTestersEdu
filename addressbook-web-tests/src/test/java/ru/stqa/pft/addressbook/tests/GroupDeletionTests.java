package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTests extends TestBase {

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
    public void testGroupDeletion() {
        Groups before = app.group().all();
        GroupData deletingGroup = before.iterator().next();

        app.group().delete(deletingGroup);
        app.goTo().groupPage();
        assertThat(app.group().count(), equalTo(before.size() - 1));

        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(deletingGroup)));
    }


}