package com.company;

public class Table extends Furniture {
    int hight;
    String material;
    public Table(String n, int h, int sq, String mtrl) {
        if((h <= 0) || (sq <= 0)) {
            throw new IllegalArgumentException();
        }
        name = n;
        hight = h;
        square = sq;
        material = mtrl;
    }
}
