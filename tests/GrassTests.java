import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class GrassTests {
    private World world;

    @Before
    public void setup() {
        world = new World(2,2);
        Grass grass = new Grass(new Position(0, 0), world);
        world.addOrganism(grass);
    }

    @Test
    public void GrassReproducing() {
        for (int i = 0; i < 20; i++) {
            world.makeTurn();
        }
        int organisms = world.getOrganisms().size();

        assertEquals(4, organisms);
    }
}
