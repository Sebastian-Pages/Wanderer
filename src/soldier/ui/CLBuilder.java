package soldier.ui;

import soldier.core.DisplayBuilder;
import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.gameManagment.Position;

public class CLBuilder { //implements DisplayBuilder {


    public void displayIdleUnit(Unit u, Position p, int rank) {
        System.out.print("[Unit:"+u.getName()+" offset: "+rank+" ]");
    }


    public void displayUnitGroup(UnitGroup a, Position p) {
        System.out.println("[UnitGroup:"+a.getName()+" position: "+p+" ]");
    }
}
