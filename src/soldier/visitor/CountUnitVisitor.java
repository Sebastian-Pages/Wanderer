package soldier.visitor;

import soldier.core.UnitGroup;
import soldier.core.UnitInfantry;
import soldier.core.UnitRider;

public class CountUnitVisitor implements UnitVisitor{
	private int count;
	
	public CountUnitVisitor() {
		count = 0;
	}
	@Override
	public void visit(UnitGroup unitGroup) {
		// TODO Auto-generated method stub
		
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
