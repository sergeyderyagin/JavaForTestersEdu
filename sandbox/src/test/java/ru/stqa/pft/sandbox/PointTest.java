package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    @Test
    public static void checkDistanceBetweenPoints() {
        Point point1 = new Point(1, 1);

        Assert.assertEquals(point1.distance(2, 9), 8.06225774829855);
        Assert.assertEquals(point1.distance(-12, -5), 14.317821063276353);

    }
}
