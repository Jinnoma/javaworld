import java.util.ArrayList;

public abstract class Organism {
    private int power;
    private int initiative;
    private Position position;
    private int liveLength;
    private int powerToReproduce;
    private String sign;
    private World world;

    public Organism(int power, int initiative, Position position, int liveLength, int powerToReproduce, String sign, World world) {
        this.power = power;
        this.initiative = initiative;
        this.position = position;
        this.liveLength = liveLength;
        this.powerToReproduce = powerToReproduce;
        this.sign = sign;
        this.world = world;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getLiveLength() {
        return liveLength;
    }

    public void setLiveLength(int liveLength) {
        this.liveLength = liveLength;
    }

    public int getPowerToReproduce() {
        return powerToReproduce;
    }

    public void setPowerToReproduce(int powerToReproduce) {
        this.powerToReproduce = powerToReproduce;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public abstract ArrayList<Action> move();

    public abstract ArrayList<Action> action();

    public abstract void initParams();

    public abstract Organism cloned();

    public ArrayList<Action> consequences(Organism attackingOrganism) {
        ArrayList<Action> result = new ArrayList<>();
        if (this.power > attackingOrganism.getPower()) {
            result.add(new Action(ActionEnum.REMOVE, new Position(-1, -1), 0, attackingOrganism));
        } else {
            result.add(new Action(ActionEnum.REMOVE, new Position(-1, -1), 0, this));
        }
        return result;
    }

    public boolean ifReproduce() {
        boolean result = false;
        if (this.power >= this.powerToReproduce) {
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        return String.format("%s: power: %d initiative: %d liveLength: %d position: %s", className, this.power, this.initiative, this.liveLength, this.position);
    }

}
