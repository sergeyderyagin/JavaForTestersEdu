package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {
        // First part of second task
        Point point1 = new Point(1, 1);
        Point point2 = new Point(5, 5);
        System.out.println(distance(point1, point2));

        // second part of second task
        System.out.println(point1.distance(point2));

	}

    public static double distance(Point first, Point second) {

	    return Math.sqrt( ((second.x - first.x) * (second.x - first.x)) + ((second.y - first.y) * (second.y - first.y)) );
    }
}