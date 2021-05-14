package soldier.ui;

import soldier.core.DisplayBuilder;
import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.gameManagment.Map;
import soldier.gameManagment.Player;
import soldier.visitor.DisplayVisitor;

public class CLBuilder implements DisplayBuilder {
    @Override
    public void displayArmy(UnitGroup army) {
        String result="\n";
        result=result+"Army:"+army.getName()+" Contains: ";
        System.out.print(result);
    }

    @Override
    public void displayScore(Player p) {

    }

    @Override
    public void displayHealth(UnitGroup a) {

    }

    @Override
    public void displayRange(UnitGroup a) {

    }

    @Override
    public void displayIdleUnit(Unit u) {
        System.out.print("[Unit:"+u.getName()+" ]");
    }

    @Override
    public void displayBackground(Map m) {

    }
}
