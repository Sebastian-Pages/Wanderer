package soldier.visitor;

import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.core.UnitInfantry;
import soldier.core.UnitRider;
import soldier.gameManagment.Position;

import java.util.Set;

public class CountUnitVisitor implements UnitVisitor{
	private int count;
	
	public CountUnitVisitor() {
		count = 0;
	}
	@Override
	public Set<Unit> visit(Set<Unit> units) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visit(UnitInfantry unitInfantry) {
		// TODO Auto-generated method stub
		count = getCount() + 1;
	}

	@Override
	public void visit(UnitRider unitRider) {
		// TODO Auto-generated method stub
		count = getCount() + 1;
	}

	public int getCount() {
		return count;
	}
	

}
