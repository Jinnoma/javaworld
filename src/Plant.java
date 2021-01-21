import java.util.ArrayList;
import java.util.Random;

public abstract class Plant extends Organism {

    public Plant(int power, int initiative, Position position, int liveLength, int powerToReproduce, String sign, World world) {
        super(power, initiative, position, liveLength, powerToReproduce, sign, world);
    }

    @Override
    public ArrayList<Action> move(){
        return new ArrayList<>();
    }

    @Override
    public ArrayList<Action> action() {
        ArrayList<Action> result = new ArrayList<>();
        Organism newPlant;
        Position newPosition;

        if (this.ifReproduce()) {
            ArrayList<Position> pomPositions = this.getFreeNeighboringPosition(this.getPosition());
            if (!pomPositions.isEmpty()) {
                Random rand = new Random();
                int newPositionIndex = rand.nextInt(pomPositions.size());
                newPosition = pomPositions.get(newPositionIndex);
                newPlant = this.cloned();
                newPlant.initParams();
                newPlant.setPosition(newPosition);
                this.setPower(this.getPower() / 2);
                result.add(new Action(ActionEnum.ADD, newPosition, 0, newPlant));
            }
        }
        return result;
    }

    public ArrayList<Position> getFreeNeighboringPosition(Position position) {
        return this.getWorld().filterFreePositions(this.getWorld().getNeighboringPositions(position));
    }
}
