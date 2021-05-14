package soldier.core;

import soldier.gameManagment.Map;
import soldier.gameManagment.Player;
import soldier.gameManagment.Position;

public interface DisplayBuilder {
    void displayIdleUnit(Unit u,Position p,int rank);
    void displayUnitGroup(UnitGroup a, Position p);


}
