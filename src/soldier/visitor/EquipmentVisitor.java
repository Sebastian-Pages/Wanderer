package soldier.visitor;

import soldier.core.EquipmentAttack;
import soldier.core.EquipmentDefense;
import soldier.core.EquipmentToy;

public interface EquipmentVisitor {
	
	void visitEquipmentAttack(EquipmentAttack equipmentAttack);
	void visitEquipmentDefense(EquipmentDefense equipmentDefense);
	void visitEquipmentToy(EquipmentToy equipmentToy);

}
