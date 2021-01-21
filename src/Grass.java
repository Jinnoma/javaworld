public class Grass extends Plant {
    public Grass(Position position, World world) {
        super(0, 0, position, 6, 3, "G", world);
    }

    @Override
    public Grass cloned() {
        return new Grass(this.getPosition(), this.getWorld());
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(0);
        this.setLiveLength(6);
        this.setPowerToReproduce(3);
        this.setSign("G");
    }
}
