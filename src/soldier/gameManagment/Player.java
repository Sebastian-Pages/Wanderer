package soldier.gameManagment;

import soldier.core.DisplayBuilder;
import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.visitor.GetterVisitor;

import java.util.Iterator;

public class Player {
    private String name;
    private UnitGroup army;
    private int score;
    private Position position;
    private Position destination;

    public Player(String name, UnitGroup army, int score, Position position, Position destination) {
        this.name = name;
        this.army = army;
        this.score = score;
        this.position = position;
        this.destination = destination;
    }

    public void displayPlayer(DisplayBuilder builder){
        GetterVisitor gv = new GetterVisitor();
        int rank = 0;
        builder.displayUnitGroup(army, position);
        for (Iterator<Unit> it = this.army.subUnits(); it.hasNext(); ) {
            //System.out.println( ""+it.next().getName()+"  "+rank);
            builder.displayIdleUnit(it.next(),position,rank);
            ++rank;
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public UnitGroup getArmy() {
        return army;
    }

    public void setArmy(UnitGroup army) {
        this.army = army;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getDestination() {
        return destination;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }
}
