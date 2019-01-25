package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();

        GroupData newGroup = new GroupData().withName("test2name").withHeader("test2header").withFooter("test2footer");

        app.group().create(newGroup);
        app.goTo().groupPage();

        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        newGroup.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());

        before.add(newGroup);
        Assert.assertEquals(before, after);
    }

}