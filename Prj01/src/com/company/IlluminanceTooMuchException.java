package com.company;

public class IlluminanceTooMuchException extends Exception {
    public IlluminanceTooMuchException(int l) {
        System.out.println("Illumination of the room mustn't be bigger than 4000 lx");
        System.out.println("Now illumination is " + l + " lx");
    }
}
