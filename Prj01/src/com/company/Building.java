package com.company;

import java.util.ArrayList;

public class Building {
    String name;
    ArrayList<Room> rooms;

    public Building(String name) {
        try {
            this.name = name;
        } catch (Exception e) {}
    }

    public void addingRooms(Room room) {
        rooms.add(room);
    }

    public void describe() {
        System.out.println(name);
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println("    " + rooms.get(i).name);
            System.out.println("        Освещенность = " + rooms.get(i).light + " (" + rooms.get(i).numberOfWindows + " окна по 700 лк");
            if (rooms.get(i).bulbs.size() == 0) {
                System.out.print(")");
            }
            else {
                System.out.print(", лампочки ");
                for (int j = 0; j < rooms.get(i).bulbs.size(); i++) {
                    System.out.print(rooms.get(i).bulbs.get(j).light);
                    if ((rooms.get(i).bulbs.size() != 1) || (rooms.get(i).bulbs.size() != (j+1))) {
                        System.out.print(", ");
                    }
                }
                System.out.print(" лк)");
            }
            System.out.println("        Площадь = " + rooms.get(i).square + " (занято " + rooms.get(i).borrowedSquare + " м^2, гарантировано свободно " + (rooms.get(i).square - rooms.get(i).borrowedSquare) + " или " + (rooms.get(i).borrowedSquare / rooms.get(i).square) + "%)");
            if (rooms.get(i).furniture.size() == 0) {
                System.out.println("Мебели нет");
            }
            else {
                System.out.println("        Мебель:");
                for (int k = 0; k < rooms.get(i).furniture.size(); k++) {
                    System.out.println("            " + rooms.get(i).furniture.get(k).name + "(площадь " + rooms.get(i).furniture.get(k).square + ")");
                }
            }
        }
    }
}
