import java.util.ArrayList;

public class Sheep extends Animal {
    public Sheep(Position position, World world) {
        super(3, 3, position, 10, 6, "S", world);
    }

    @Override
    public Sheep cloned() {
        return new Sheep(this.getPosition(), this.getWorld());
    }

    @Override
    public void initParams() {
        this.setPower(3);
        this.setInitiative(3);
        this.setLiveLength(10);
        this.setPowerToReproduce(6);
        this.setSign("S");
    }

    @Override
    public ArrayList<Position> getNeighboringPositions() {
        return this.getWorld().filterPositionsWithoutAnimals(this.getWorld().getNeighboringPositions(this.getPosition()));
    }
}
