package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class GroupCreationTests extends TestBase {
    private Comparator<? super GroupData> byIdComparator = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();

        GroupData newGroup = new GroupData().withName("test2name").withHeader("test2header").withFooter("test2footer");
        app.group().create(newGroup);
        app.goTo().groupPage();


        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(newGroup);
        before.sort(byIdComparator);
        after.sort(byIdComparator);
        Assert.assertEquals(before, after);
    }


}
