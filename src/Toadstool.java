import java.util.ArrayList;

public class Toadstool extends Plant {
    public Toadstool(Position position, World world) {
        super(0, 0, position, 10, 5, "T", world);
    }

    @Override
    public Organism cloned() {
        return new Toadstool(this.getPosition(), this.getWorld());
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(0);
        this.setLiveLength(10);
        this.setPowerToReproduce(5);
        this.setSign("T");
    }

    @Override
    public ArrayList<Action> consequences(Organism attackingOrganism) {
        ArrayList<Action> result = new ArrayList<>();

        if (this.getPower() <= attackingOrganism.getPower()) {
            result.add(new Action(ActionEnum.REMOVE, new Position(-1, -1), 0, this));
        }
        result.add(new Action(ActionEnum.REMOVE, new Position(-1, -1), 0, attackingOrganism));
        return result;
    }
}
