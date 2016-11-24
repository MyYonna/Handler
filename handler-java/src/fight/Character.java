package fight;

public abstract class Character {

	WeaponBehavior weapon;
	
	
	public WeaponBehavior getWeapon() {
		return weapon;
	}


	public void setWeapon(WeaponBehavior weapon) {
		this.weapon = weapon;
	}


	public abstract void fight();
	
}
