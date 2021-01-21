public class Action {
    private final ActionEnum action;
    private final Position position;
    private final int value;
    private final Organism organism;

    public Action(ActionEnum action, Position position, int value, Organism organism) {
        this.action = action;
        this.position = position;
        this.value = value;
        this.organism = organism;
    }

    public ActionEnum getAction() {
        return action;
    }

    public Position getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public Organism getOrganism() {
        return organism;
    }

    @Override
    public String toString() {
        String className = this.organism.getClass().getSimpleName();

        return switch (action) {
            case ADD -> String.format("%s add at %s", className, this.position);
            case INCREASEPOWER -> String.format("%s increase power: %s", className, this.position);
            case MOVE -> String.format("%s move from: %s to: %s", className, this.organism.getPosition(), this.position);
            case REMOVE -> String.format("%s remove from: %s", className, this.organism.getPosition());
        };
    }
}
