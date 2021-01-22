import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class ToadstoolTests {
    private World world;

    @Before
    public void setup() {
        world = new World(2,1);
        Toadstool toadstool = new Toadstool(new Position(0, 0), world);
        world.addOrganism(toadstool);
    }

    @Test
    public void toadstoolReproducing() {
        for (int i = 0; i < 10; i++) {
            world.makeTurn();
        }
        int organismsAmount = world.getOrganisms().size();
        assertEquals(1, organismsAmount);
    }

    @Test
    public void wolfAttackToadstool() {
        Organism wolf = OrganismsFactory.getOrganism("Wolf", new Position(1, 0), world);
        world.addOrganism(wolf);

        world.makeTurn();

        int organismsAmount = world.getOrganisms().size();
        assertEquals(0, organismsAmount);
    }

    @Test
    public void sheepEatToadstool() {
        Organism sheep = OrganismsFactory.getOrganism("Sheep", new Position(1, 0), world);
        world.addOrganism(sheep);

        world.makeTurn();

        int organismsAmount = world.getOrganisms().size();
        assertEquals(0, organismsAmount);
    }
}
