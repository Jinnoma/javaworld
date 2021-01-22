import java.util.ArrayList;

public class World {
    private int worldX;
    private int worldY;
    private int turn;
    private ArrayList<Organism> organisms;
    private ArrayList<Organism> newOrganisms;
    private String separator;

    public World(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.turn = 0;
        this.organisms = new ArrayList<>();
        this.newOrganisms = new ArrayList<>();
        this.separator = ".";
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public void setOrganisms(ArrayList<Organism> organisms) {
        this.organisms = organisms;
    }

    public ArrayList<Organism> getNewOrganisms() {
        return newOrganisms;
    }

    public void setNewOrganisms(ArrayList<Organism> newOrganisms) {
        this.newOrganisms = newOrganisms;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public void makeTurn() {
        ArrayList<Action> actions;

        for (Organism org : this.organisms) {
            if (this.positionOnBoard(org.getPosition())) {
                actions = org.move();
                for (Action a : actions) {
                    this.makeMove(a);
                }
                actions.clear();
                if (this.positionOnBoard(org.getPosition())) {
                    actions = org.action();
                    for (Action a : actions) {
                        this.makeMove(a);
                    }
                }
            }
        }

        ArrayList<Organism> organismsOnBoard = new ArrayList<>();
        for (Organism o : this.organisms) {
            if (this.positionOnBoard(o.getPosition())) {
                organismsOnBoard.add(o);
            }
        }

        this.setOrganisms(organismsOnBoard);

        for (Organism o : this.organisms) {
            o.setLiveLength(o.getLiveLength() - 1);
            o.setPower(o.getPower() + 1);
        }

        ArrayList<Organism> organismsAlive = new ArrayList<>();
        for (Organism o : this.organisms) {
            if (o.getLiveLength() > 0) {
                organismsAlive.add(o);
            } else {
                System.out.println(o.getClass().getSimpleName() + ": died of old age at: " + o.getPosition());
            }
        }
        this.setOrganisms(organismsAlive);
        ArrayList<Organism> newOrganisms = new ArrayList<>();

        for (Organism o : this.newOrganisms) {
            if (this.positionOnBoard(o.getPosition())) {
                newOrganisms.add(o);
            }
        }
        this.setNewOrganisms(newOrganisms);
        this.organisms.addAll(newOrganisms);
        this.newOrganisms.clear();
        this.setTurn(this.getTurn() + 1);
    }

    public void makeMove(Action action) {
        System.out.println(action);
        if (action.getAction() == ActionEnum.ADD) {
            this.newOrganisms.add(action.getOrganism());
            this.setNewOrganisms(this.newOrganisms);
        } else if (action.getAction() == ActionEnum.INCREASEPOWER) {
            action.getOrganism().setPower(action.getOrganism().getPower() + 1);
        } else if (action.getAction() == ActionEnum.MOVE) {
            action.getOrganism().setPosition(action.getPosition());
        } else if (action.getAction() == ActionEnum.REMOVE) {
            action.getOrganism().setPosition(new Position(-1, -1));
        }
    }

    public boolean addOrganism(Organism newOrganism) {
        Position newOrgPosition = new Position(newOrganism.getPosition().getX(), newOrganism.getPosition().getY());

        if (this.positionOnBoard(newOrgPosition)) {
            ArrayList<Organism> currentOrganisms = this.getOrganisms();
            currentOrganisms.add(newOrganism);
            this.setOrganisms(currentOrganisms);
            return true;
        }
        return false;
    }

    public boolean positionOnBoard(Position position) {
        return position.getX() >= 0 && position.getY() >= 0 && position.getX()
                < this.worldX && position.getY() < this.worldY;
    }

    public Organism getOrganismFromPosition(Position position) {
        Organism pomOrganism = null;

        for (Organism org : this.organisms) {
            if (org.getPosition().getX() == position.getX() && org.getPosition().getY() == position.getY()) {
                pomOrganism = org;
                break;
            }
        }
        if (pomOrganism == null) {
            for (Organism org : this.newOrganisms) {
                if (org.getPosition().getX() == position.getX() && org.getPosition().getY() == position.getY()) {
                    pomOrganism = org;
                    break;
                }
            }
        }
        return pomOrganism;
    }

    public ArrayList<Position> getNeighboringPositions(Position position) {
        ArrayList<Position> result = new ArrayList<>();
        Position pomPosition;

        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                pomPosition = new Position(position.getX() + x, position.getY() + y);
                if (this.positionOnBoard(pomPosition) && !(y == 0 && x == 0)) {
                    result.add(pomPosition);
                }
            }
        }

        return result;
    }

    public ArrayList<Position> filterFreePositions(ArrayList<Position> fields) {
        ArrayList<Position> result = new ArrayList<>();
        Organism pomOrg;

        for (Position field : fields) {
            pomOrg = this.getOrganismFromPosition(field);
            if (pomOrg == null) {
                result.add(field);
            }
        }
        return result;
    }

    public ArrayList<Position> filterPositionsWithoutAnimals(ArrayList<Position> fields) {
        ArrayList<Position> result = new ArrayList<>();
        Organism pomOrg;

        for (Position field : fields) {
            pomOrg = this.getOrganismFromPosition(field);
            if (pomOrg == null || pomOrg instanceof Plant) {
                result.add(field);
            }
        }
        return result;
    }

    public ArrayList<Position> getAllPositions() {
        ArrayList<Position> allPositions = new ArrayList<Position>();
        for (int x = 0; x < this.worldX; x++) {
            for (int y = 0; y < this.worldY; y++) {
                allPositions.add(new Position(x, y));
            }
        }

        return allPositions;
    }


    public ArrayList<Position> filterPositionsWithOtherSpecies(ArrayList<Position> fields, Class<?> species) {
        ArrayList<Position> result = new ArrayList<>();
        Organism pomOrg;

        for (Position field : fields) {
            pomOrg = this.getOrganismFromPosition(field);
            if (pomOrg == null || pomOrg.getClass() != species) {
                result.add(field);
            }
        }
        return result;
    }


    @Override
    public String toString() {
        String result = "\nturn: " + this.turn + "\n";
        for (int wY = 0; wY < this.worldY; wY++) {
            for (int wX = 0; wX < this.worldX; wX++) {
                Organism org = this.getOrganismFromPosition(new Position(wX, wY));
                if (org != null) {
                    result += org.getSign();
                } else {
                    result += this.separator;
                }
            }
            result += "\n";
        }
        return result;
    }
}
