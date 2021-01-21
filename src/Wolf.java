import java.util.ArrayList;

public class Wolf extends Animal {

    public Wolf(Position position, World world) {
        super(6, 5, position, 15, 12, "W", world);
    }

    @Override
    public Wolf cloned() {
        return new Wolf(this.getPosition(), this.getWorld());
    }

    @Override
    public void initParams() {
        this.setPower(6);
        this.setInitiative(5);
        this.setLiveLength(15);
        this.setPowerToReproduce(12);
        this.setSign("W");
    }
    @Override
    public ArrayList<Position> getNeighboringPositions() {
        return this.getWorld().filterPositionsWithOtherSpecies(this.getWorld().getNeighboringPositions(this.getPosition()), Wolf.class);
    }
}
