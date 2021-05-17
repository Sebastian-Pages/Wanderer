package soldier.units;

import soldier.core.EquipmentException;
import soldier.core.UnitInfantry;
import soldier.core.Equipment;

public class UnitBoss extends UnitInfantry {

    public UnitBoss(String soldierName) {
        super(soldierName, new BehaviorSoldierStd(1500, 50));
    }

    /**
     * A Boss can have at most two equipments
     */
    @Override
    public void addEquipment(Equipment w) {
        if (nbWeapons() > 2)
            throw new EquipmentException();
        super.addEquipment(w);
    }

}
