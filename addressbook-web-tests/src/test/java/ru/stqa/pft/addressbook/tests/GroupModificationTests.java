package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;

public class GroupModificationTests extends TestBase {
    private Comparator<? super GroupData> comparatorById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());

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
    public void testGroupModification() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;

        GroupData editedGroup = new GroupData()
                .withId(before.get(index).getId()).withName("test2name").withHeader("test2header").withFooter("test2footer");

        app.group().modify(index, editedGroup);
        app.goTo().groupPage();

        List<GroupData> after = app.group().list();

        before.remove(index);
        before.add(editedGroup);
        before.sort(comparatorById);
        after.sort(comparatorById);
        Assert.assertEquals(before, after);

    }

}
