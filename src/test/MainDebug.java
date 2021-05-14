/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universit� Bordeaux.
 */
package test;

import soldier.ages.AgeFutureFactory;
import soldier.ages.AgeMiddleFactory;
import soldier.core.*;
import soldier.gameManagment.Player;
import soldier.gameManagment.Position;
import soldier.ui.CLBuilder;

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
		//builder de type command line
		DisplayBuilder builder = new CLBuilder();
		Position initPos = new Position(0,0);

		AgeAbstractFactory age1 = new AgeMiddleFactory();
		AgeAbstractFactory age2 = new AgeFutureFactory();

		UnitGroup team1 = createTeam1(age1);
		UnitGroup  team2 = createTeam2(age2);

		Player player1 = new Player("Patrick",team1,0,initPos,initPos);
		player1.displayPlayer(builder);
	}

}
