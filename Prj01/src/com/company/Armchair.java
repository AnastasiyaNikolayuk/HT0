package com.company;

public class Armchair extends Furniture {
    String upholstery, filling;
    public Armchair(String n, String up, String fil, int sq) {
        if(sq <= 0) {
            throw new IllegalArgumentException();
        }
        name = n;
        upholstery = up;
        filling = fil;
        square = sq;
    }
}
