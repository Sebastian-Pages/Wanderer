package soldier.visitor;

import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.core.UnitInfantry;
import soldier.core.UnitRider;
import soldier.gameManagment.Position;

import java.util.Set;

public interface UnitVisitor {
	
	Set<Unit> visit(Set<Unit> units);
	void visit(UnitInfantry unitInfantry);
	void visit(UnitRider unitRider);
}
