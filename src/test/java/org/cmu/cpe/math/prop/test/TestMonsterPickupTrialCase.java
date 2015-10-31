package org.cmu.cpe.math.prop.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.cmu.cpe.math.prop.game.MonsterPickupCriteria;
import org.cmu.cpe.math.prop.game.MonsterPickupTrial;
import org.junit.Test;

public class TestMonsterPickupTrialCase{
	public static final int NUM_TRIAL = 1000000;
	public static final int TOTAL_MONSTER = 100;
	public static final int NEW_MONSTER = 5;
	
	protected static class CaseMeetCriteria implements MonsterPickupCriteria{
		private int meet;
		
		public Map<Integer, Integer> pickupMap = new HashMap<>();
		
		public CaseMeetCriteria(int meet){
			this.meet = meet;
		}
		
		@Override
		public boolean finished(int pickup, boolean[] meets, int[] feqs){
			int count = 0;
			for(int i = 0; i < meets.length; i++){
				if(meets[i]) count++;
			}
			
			boolean isFinished = (count == this.meet);
			
			if(isFinished){
				if(pickupMap.containsKey(pickup)){
					pickupMap.put(pickup, pickupMap.get(pickup) + 1);
				} else{
					pickupMap.put(pickup, 1);
				}
			}
			
			return isFinished;
		}
	}
	
	@Test
	public void case01Test(){
		int pickupPrice = 10;

		MonsterPickupTrial monsterPickupTrial = new MonsterPickupTrial(TOTAL_MONSTER, NEW_MONSTER, 5);
		
		CaseMeetCriteria case01Criteria = new CaseMeetCriteria(NEW_MONSTER);
		monsterPickupTrial.trial(case01Criteria, NUM_TRIAL);
		
		double expectedValue = 0;
		for(Entry<Integer, Integer> entry: case01Criteria.pickupMap.entrySet()){
			//System.out.println(entry.getKey() + " " + entry.getValue());
			expectedValue += ((pickupPrice * entry.getKey()) * ((double)entry.getValue()/NUM_TRIAL));
		}
		
		System.out.println("Case01 Expected Value: " + expectedValue);
	}

	@Test
	public void case02Test(){
		int pickupPrice = 5;

		MonsterPickupTrial monsterPickupTrial = new MonsterPickupTrial(TOTAL_MONSTER, NEW_MONSTER);
		
		CaseMeetCriteria case01Criteria = new CaseMeetCriteria(NEW_MONSTER);
		monsterPickupTrial.trial(case01Criteria, NUM_TRIAL);
		
		double expectedValue = 0;
		for(Entry<Integer, Integer> entry: case01Criteria.pickupMap.entrySet()){
			//System.out.println(entry.getKey() + " " + entry.getValue());
			expectedValue += ((pickupPrice * entry.getKey()) * ((double)entry.getValue()/NUM_TRIAL));
		}
		
		System.out.println("Case02 Expected Value: " + expectedValue);
	}

	@Test
	public void case03Test(){
		int pickupPrice = 10;
		int pickupExtra = 10;
		MonsterPickupTrial extraMonsterPickupTrial = new MonsterPickupTrial(TOTAL_MONSTER, NEW_MONSTER, 1, 0);

		class Case01Criteria implements MonsterPickupCriteria{
			public Map<Integer, Integer> pickupMap = new HashMap<>();
			
			@Override
			public boolean finished(int pickup, boolean[] meets, int[] feqs){
				if((pickup > 0) && ((pickup % pickupExtra) == 0)){
					int mon = extraMonsterPickupTrial.pickup();
					if(mon < NEW_MONSTER) meets[mon] = true;
				}
				int count = 0;
				for(int i = 0; i < meets.length; i++){
					if(meets[i]) count++;
				}
				
				boolean isFinished = (count == NEW_MONSTER);
				
				if(isFinished){
					if(pickupMap.containsKey(pickup)){
						pickupMap.put(pickup, pickupMap.get(pickup) + 1);
					} else{
						pickupMap.put(pickup, 1);
					}
				}
				
				return isFinished;
			}
		};
		
		MonsterPickupTrial monsterPickupTrial = new MonsterPickupTrial(TOTAL_MONSTER, NEW_MONSTER);
		
		Case01Criteria case01Criteria = new Case01Criteria();
		monsterPickupTrial.trial(case01Criteria, NUM_TRIAL);
		
		double expectedValue = 0;
		for(Entry<Integer, Integer> entry: case01Criteria.pickupMap.entrySet()){
			//System.out.println(entry.getKey() + " " + entry.getValue());
			expectedValue += ((pickupPrice * entry.getKey()) * ((double)entry.getValue()/NUM_TRIAL));
		}
		
		System.out.println("Case03 Expected Value: " + expectedValue);
	}
}
