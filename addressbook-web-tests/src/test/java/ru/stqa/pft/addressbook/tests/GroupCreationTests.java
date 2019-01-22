package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();

        GroupData newGroup = new GroupData("test2", null, null);
        app.getGroupHelper().createGroup(newGroup);

        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        int idMax = 0;
        for (GroupData g : after) {
            if (g.getId() > idMax) {
                idMax = g.getId();
            }
        }


        newGroup.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

        before.add(newGroup);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }


}
