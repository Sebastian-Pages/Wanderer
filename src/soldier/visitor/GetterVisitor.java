package soldier.visitor;

import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.core.UnitInfantry;
import soldier.core.UnitRider;

import java.util.Set;
import java.util.TreeSet;

public class GetterVisitor implements UnitVisitor {
    @Override
    public Set<Unit> visit(Set<Unit>  units) {
        return units;
    }


    @Override
    public void visit(UnitInfantry unitInfantry) {

    }

    @Override
    public void visit(UnitRider unitRider) {

    }
}
