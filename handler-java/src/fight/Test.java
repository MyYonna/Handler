package fight;

public class Test {

	public static void main(String[] args){
		Character c = new King();
		WeaponBehavior weapon = new SwordBehavior();
		c.setWeapon(weapon);
		c.fight();
		WeaponBehavior weapon1 = new KnifeBehavior();
		c.setWeapon(weapon1);
		c.fight();
	}
}
