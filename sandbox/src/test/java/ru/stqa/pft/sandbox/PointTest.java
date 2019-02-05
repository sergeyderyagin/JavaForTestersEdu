package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    @Test
    public static void checkDistanceBetweenPoints() {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 9);
        Point point3 = new Point(-12, -5);

        Assert.assertEquals(point1.distance(point2), 8.06225774829855);
        Assert.assertEquals(point1.distance(point3), 14.317821063276353);

    }
}
