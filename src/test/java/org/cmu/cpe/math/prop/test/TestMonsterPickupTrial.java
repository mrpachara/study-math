package org.cmu.cpe.math.prop.test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.cmu.cpe.math.prop.game.MonsterPickupTrial;
import org.junit.Test;

public class TestMonsterPickupTrial{
	public static final int NUM_TRIAL = 1000000;
	private static final NumberFormat formatter = new DecimalFormat("#0.0000");
	
	@Test
	public void propFnTest(){
		MonsterPickupTrial monsterPickupTrial = new MonsterPickupTrial(100, 5);
		
		double[] fn = monsterPickupTrial.trial(MonsterPickupTrial.getPickupLimitCriteria(20), NUM_TRIAL);
		double expectedValue = 0;
		for(int i = 0; i < fn.length; i ++){
			System.out.println(String.valueOf(i) + " " + formatter.format(fn[i]));
			expectedValue += (i * fn[i]);
		}
		
		System.out.println("Expected Value: " + formatter.format(expectedValue));
	}
}
