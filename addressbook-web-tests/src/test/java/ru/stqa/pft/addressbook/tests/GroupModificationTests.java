package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        // Создание группы, если группа отсутствует.
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
            app.getNavigationHelper().gotoGroupPage();
        }

        List<GroupData> before = app.getGroupHelper().getGroupList();

        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();

        GroupData newGroup = new GroupData(before.get(before.size() - 1).getId(), "test1_edited", "test2_edited", "test3_edited");
        app.getGroupHelper().fillGroupForm(newGroup);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();

        // При изменении группы (имени группы), приложением меняет порядок.
        before.remove(before.size() - 1);
        before.add(newGroup);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
