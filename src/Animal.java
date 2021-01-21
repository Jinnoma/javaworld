import java.util.ArrayList;
import java.util.Random;

public abstract class Animal extends Organism  {
    private Position lastPosition;

    public Animal(int power, int initiative, Position position, int liveLength, int powerToReproduce, String sign, World world) {
        super(power, initiative, position, liveLength, powerToReproduce, sign, world);
        this.lastPosition = position;
    }

    public Position getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Position lastPosition) {
        this.lastPosition = lastPosition;
    }

    public ArrayList<Action> move() {
        ArrayList<Action> result = new ArrayList<>();
        ArrayList<Position> pomPositions = this.getNeighboringPositions();
        Position newPosition;


        if (!pomPositions.isEmpty()) {
            Random rand = new Random();
            int newPositionInt = rand.nextInt(pomPositions.size());
            newPosition = pomPositions.get(newPositionInt);
            result.add(new Action(ActionEnum.MOVE, newPosition, 0, this));
            this.setLastPosition(this.getPosition());
            Organism metOrganism = this.getWorld().getOrganismFromPosition(newPosition);
            if (metOrganism != null) {
                result.addAll(metOrganism.consequences(this));
            }
        }
        return result;
    }

    public ArrayList<Action> action () {
        ArrayList<Action> result = new ArrayList<>();

        Organism newAnimal;
        ArrayList<Position> birthPositions = this.getNeighboringBirthPosition();

        if (this.ifReproduce() && !birthPositions.isEmpty()) {
            Random random = new Random();
            int newPositionIndex = random.nextInt(birthPositions.size());
            Position newAnimalPosition = birthPositions.get(newPositionIndex);
            newAnimal = this.cloned();
            newAnimal.initParams();
            newAnimal.setPosition(newAnimalPosition);
            this.setPower(this.getPower() / 2);
            result.add(new Action(ActionEnum.ADD, newAnimalPosition, 0, newAnimal));
        }

        return result;
    }

    public ArrayList<Position> getNeighboringPositions () {
        return this.getWorld().getNeighboringPositions(this.getPosition());
    }

    public ArrayList<Position> getNeighboringBirthPosition () {
        return this.getWorld().filterFreePositions(this.getWorld().getNeighboringPositions(this.getPosition()));
    }
}
