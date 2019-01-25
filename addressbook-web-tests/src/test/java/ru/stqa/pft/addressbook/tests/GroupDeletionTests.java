package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;


public class GroupDeletionTests extends TestBase {

    /**
     * Создание группы, если группа отсутствует.
     */
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test2name").withHeader("test2header").withFooter("test2footer"));
            app.goTo().groupPage();
        }
    }

    @Test
    public void testGroupDeletion() {
        Set<GroupData> before = app.group().all();
        GroupData deletingGroup = before.iterator().next();

        app.group().delete(deletingGroup);
        app.goTo().groupPage();

        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletingGroup);
        Assert.assertEquals(before, after);
    }


}