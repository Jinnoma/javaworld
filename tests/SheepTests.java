import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class SheepTests {
    private World world;

    @Before
    public void setup() {
        world = new World(2,1);
        Sheep sheep = new Sheep(new Position(0, 0), world);
        world.addOrganism(sheep);
    }

    @Test
    public void sheepEatingGrass() {
        Organism grass = OrganismsFactory.getOrganism("Grass", new Position(1,0), world);
        world.addOrganism(grass);

        world.makeTurn();

        int result = world.getOrganisms().size();
        assertEquals(1, result);
    }

    @Test
    public void sheepEatingDandelion() {
        Organism dandelion = OrganismsFactory.getOrganism("Dandelion", new Position(1,0), world);
        world.addOrganism(dandelion);

        world.makeTurn();

        int result = world.getOrganisms().size();
        assertEquals(1, result);
    }

    @Test
    public void wolfAttackSheep() {
        Organism wolf = OrganismsFactory.getOrganism("Wolf", new Position(1,0), world);
        world.addOrganism(wolf);

        world.makeTurn();

        String result = world.getOrganisms().get(0).getSign();
        assertEquals("W", result);
    }
}
