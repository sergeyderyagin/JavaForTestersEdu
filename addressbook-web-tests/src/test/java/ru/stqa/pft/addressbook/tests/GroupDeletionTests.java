package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class GroupDeletionTests extends TestBase {
    private Comparator<? super GroupData> comparatorById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

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
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;

        app.group().select(index);
        app.group().delete();

        app.goTo().groupPage();

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        before.sort(comparatorById);
        after.sort(comparatorById);
        Assert.assertEquals(before, after);
    }


}