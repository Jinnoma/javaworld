public class Dandelion extends Plant {
    public Dandelion(Position position, World world) {
        super(0, 0, position, 6, 2, "D", world);
    }

    @Override
    public Dandelion cloned() {
        return new Dandelion(this.getPosition(), this.getWorld());
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(0);
        this.setLiveLength(6);
        this.setPowerToReproduce(2);
        this.setSign("D");
    }
}
