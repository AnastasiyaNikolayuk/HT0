package com.company;

public class Wardrobe extends Furniture {
    String wood;
    boolean mirror;
    public Wardrobe(String n, String w, int sq, boolean m) {
        if(sq <= 0) {
            throw new IllegalArgumentException();
        }
        name = n;
        wood = w;
        square = sq;
        mirror = m;
    }
}
