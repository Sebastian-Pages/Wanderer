package soldier.core;

import soldier.gameManagment.Map;
import soldier.gameManagment.Player;

public interface DisplayBuilder {
    void displayArmy(UnitGroup a);
    void displayScore(Player p);
    void displayHealth(UnitGroup a);
    void displayRange(UnitGroup a);
    void displayIdleUnit(Unit u);
    void displayBackground(Map m);

}
