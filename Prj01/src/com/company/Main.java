package com.company;

public class Main {

    public static void main(String[] args) throws IlluminanceTooMuchException, SpaceUsageTooMuchException {
        Building building = new Building(args[0]);

        Room room1 = new Room(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        Room room2 = new Room(args[4], Integer.parseInt(args[5]), Integer.parseInt(args[6]));

        building.addingRooms(room1);
        building.addingRooms(room2);

        building.rooms.get(0).lightning(new LightBulb(Integer.parseInt(args[7])));
        building.rooms.get(0).lightning(new LightBulb(Integer.parseInt(args[8])));
        building.rooms.get(1).lightning(new LightBulb(Integer.parseInt(args[9])));

        building.rooms.get(0).addingFurniture(new Armchair(args[10], args[11], args[12], Integer.parseInt(args[13])));
        building.rooms.get(0).addingFurniture(new Wardrobe(args[14], args[15], Integer.parseInt(args[16]), Boolean.parseBoolean(args[17])));
        building.rooms.get(1).addingFurniture(new Table(args[18], Integer.parseInt(args[19]), Integer.parseInt(args[20]), args[21]));

        building.describe();
    }
}
