package soldier.visitor;

import soldier.core.DisplayBuilder;
import soldier.core.UnitGroup;
import soldier.core.UnitInfantry;
import soldier.core.UnitRider;
import soldier.ui.CLBuilder;
import soldier.ui.JFXBuilder;

public class DisplayVisitor implements UnitVisitor{
    DisplayBuilder builder = new CLBuilder();


    @Override
    public void visit(UnitGroup unitGroup) {
        builder.displayArmy(unitGroup);
    }

    @Override
    public void visit(UnitInfantry unitInfantry) {
        builder.displayIdleUnit(unitInfantry);
    }

    @Override
    public void visit(UnitRider unitRider) {
        builder.displayIdleUnit(unitRider);

    }
}
