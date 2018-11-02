package com.company;

import java.util.ArrayList;

public class Room {
    String name;
    int square, borrowedSquare = 0, numberOfWindows, light;
    double allowedSquare;
    ArrayList<Furniture> furniture;
    ArrayList<LightBulb> bulbs;
    public Room(String n, int sq, int w) throws IlluminanceTooMuchException {
        try {
            if ((sq <= 0) || (w <= 0)) {
                throw new IllegalArgumentException();
            }
            name = n;
            square = sq;
            allowedSquare = square * 0.7;
            numberOfWindows = w;
            light = w * 700;
            if (light > 4000) {
                throw new IlluminanceTooMuchException(light);
            }
            furniture = new ArrayList<>();
            bulbs = new ArrayList<>();
        } catch (Exception e) {}
    }

    public void addingFurniture(Furniture f) throws SpaceUsageTooMuchException {
        borrowedSquare += f.square;
        if (borrowedSquare > allowedSquare) {
            borrowedSquare -= f.square;
            throw new SpaceUsageTooMuchException();
        }
        else {
            furniture.add(f);
        }
    }

    public void lightning(LightBulb lb) throws IlluminanceTooMuchException {
        bulbs.add(lb);
        light += lb.light;
        if (light > 4000) {
            light -= lb.light;
            throw new IlluminanceTooMuchException(light);
        }
    }
}
