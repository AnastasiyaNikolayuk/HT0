package com.company;

public class LightBulb {
    int light;
    public LightBulb(int l) {
        try {
            if (l <= 0) {
                throw new IllegalArgumentException();
            }
            light = l;
        } catch (Exception e) {}
    }
}
