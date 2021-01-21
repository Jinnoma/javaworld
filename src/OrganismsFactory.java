//public class OrganismsFactory {
//
//    public static Organism getOrganism(String type, Position position, World world) {
//
//        if ("Wolf".equalsIgnoreCase(type)) return new Wolf(position, world);
//        else if ("Dandelion".equalsIgnoreCase(type)) return new Dandelion(position, world);
//        else if ("Sheep".equalsIgnoreCase(type)) return new Sheep(position, world);
//        else if ("Toadstool".equalsIgnoreCase(type)) return new Toadstool(position, world);
//        else if ("Grass".equalsIgnoreCase(type)) return new Grass(position, world);
//
//        return null;
//    }
//}

public class OrganismsFactory {

    public static Organism getOrganism(String type, Position position, World world) {
        if ("Wolf".equalsIgnoreCase(type)) return new Wolf(position, world);
        else if ("Dandelion".equalsIgnoreCase(type)) return new Dandelion(position, world);
        else if ("Sheep".equalsIgnoreCase(type)) return new Sheep(position, world);
        else if ("Toadstool".equalsIgnoreCase(type)) return new Toadstool(position, world);
        else if ("Grass".equalsIgnoreCase(type)) return new Grass(position, world);
        return null;

    }
}

