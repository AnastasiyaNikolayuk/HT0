package com.company;

public class SpaceUsageTooMuchException extends Exception {
    public SpaceUsageTooMuchException() {
        System.out.println("There is no place for this furniture");
    }
}
