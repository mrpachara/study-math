package org.cmu.cpe.math.prop.game;

public interface MonsterPickupCriteria{
	boolean finished(int pickup, boolean[] meets, int[] feqs);
}
