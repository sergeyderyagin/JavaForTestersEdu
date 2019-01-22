package ru.stqa.pft.sandbox;

public class Equality {

    public static void main(String[] args) {
        String s1 = "firefox 2.0";
        String s2 = "firefox";

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println("firefox" + 2);
    }
}
