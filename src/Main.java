import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        World world = new World(8, 8);

        Organism grass = OrganismsFactory.getOrganism("Grass", new Position(4, 0), world);
        world.addOrganism(grass);

        Organism sheep = OrganismsFactory.getOrganism("Sheep", new Position(0, 0), world);
        world.addOrganism(sheep);

        Organism dandelion = OrganismsFactory.getOrganism("Dandelion", new Position(0, 4), world);
        world.addOrganism(dandelion);

        Organism wolf = OrganismsFactory.getOrganism("Wolf", new Position(6, 6), world);
        world.addOrganism(wolf);

        Organism toadstool = OrganismsFactory.getOrganism("Toadstool", new Position(4, 4), world);
        world.addOrganism(toadstool);

        System.out.println(world);

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 100; i++) {
            scanner.nextLine();
            world.makeTurn();
            System.out.println(world);
        }
    }
}
