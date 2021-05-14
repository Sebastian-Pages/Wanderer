/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universit� Bordeaux.
 */
package test;

import soldier.ages.AgeFutureFactory;
import soldier.ages.AgeMiddleFactory;
import soldier.core.AgeAbstractFactory;
import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.visitor.DisplayVisitor;

public class MainDebug {

	public static UnitGroup createTeam1(AgeAbstractFactory fact) {
		UnitGroup t1 = new UnitGroup("Mammals");
		t1.addUnit(fact.infantryUnit("mouse"));
		t1.addUnit(fact.infantryUnit("cat"));
		return t1;
	}
	public static UnitGroup createTeam2(AgeAbstractFactory fact)  {
		UnitGroup t2 = new UnitGroup("Humans");
		t2.addUnit(fact.riderUnit("jenny"));
		t2.addUnit(fact.riderUnit("kenny"));
		return t2;
	}
	
	public static void main(String[] args) {

		AgeAbstractFactory age1 = new AgeMiddleFactory();
		AgeAbstractFactory age2 = new AgeFutureFactory();

		UnitGroup team1 = createTeam1(age1);
		UnitGroup  team2 = createTeam2(age2);
		
		team1.accept(new DisplayVisitor());
		team2.accept(new DisplayVisitor());
	}

}
