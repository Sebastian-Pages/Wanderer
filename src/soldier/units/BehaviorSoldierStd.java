/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universit� Bordeaux.
 */
package soldier.units;

import soldier.core.BehaviorSoldier;

public class BehaviorSoldierStd implements BehaviorSoldier {
	private float healthPoints;
	private float force;
	private float initialHealth;

	public BehaviorSoldierStd(float healthPoints, float force) {
		this.healthPoints = healthPoints;
		this.force = force;
		this.initialHealth = healthPoints;
	}

	@Override
	public float getHealthPoints() {
		return healthPoints;
	}

	@Override
	public boolean alive() {
		return getHealthPoints() > 0;
	}

	@Override
	public float parry(float force) {
		float ret = Math.max(0.f, force - healthPoints);
		healthPoints = Math.max(0.f, healthPoints - force);
		return ret;
	}

	@Override
	public float strike() {
		return alive() ? force : 0;
	}

	@Override
	public void heal() {
		healthPoints  = initialHealth;
	}

	public float initialHealth() {
		return initialHealth;
	}
}
