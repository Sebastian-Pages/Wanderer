package soldier.visitor;

import soldier.core.UnitGroup;
import soldier.core.UnitInfantry;
import soldier.core.UnitRider;

public interface UnitVisitor {
	
	void visit(UnitGroup unitGroup);
	void visit(UnitInfantry unitInfantry);
	void visit(UnitRider unitRider);

}
