package org.cmu.cpe.math.prop.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.cmu.cpe.math.prop.game.MonsterPickupCriteria;
import org.cmu.cpe.math.prop.game.MonsterPickupTrial;

public class Assignment04 implements Runnable{
	public static final String OUTPUT_FILE = "assignment04-output.csv";
	
	protected static interface RunResult{
		public void solve();
		public double getExpectedValue();
	}
	
	protected static interface FeqPickupCriteria extends MonsterPickupCriteria{
		public Map<Integer, Integer> getFeq();
	}
	
	protected static class CaseMeetCriteria implements FeqPickupCriteria{
		protected final int meet;
		
		private Map<Integer, Integer> feqMap = new HashMap<>();
		
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
				if(feqMap.containsKey(pickup)){
					feqMap.put(pickup, feqMap.get(pickup) + 1);
				} else{
					feqMap.put(pickup, 1);
				}
			}
			
			return isFinished;
		}

		@Override
		public Map<Integer, Integer> getFeq(){
			return this.feqMap;
		}
	}
	
	protected static class CaseMeetWithExtraPickupCriteria extends CaseMeetCriteria{
		protected final int newMon;
		protected final int interval;
		protected final MonsterPickupTrial extraMonsterPickupTrial;
		
		public CaseMeetWithExtraPickupCriteria(int meet, int newMon, int interval, MonsterPickupTrial extraMonsterPickupTrial){
			super(meet);
			this.newMon = newMon;
			this.interval = interval;
			this.extraMonsterPickupTrial = extraMonsterPickupTrial;
		}

		@Override
		public boolean finished(int pickup, boolean[] meets, int[] feqs){
			if((pickup > 0) && ((pickup % this.interval) == 0)){
				int mon = this.extraMonsterPickupTrial.pickup();
				if(mon < this.newMon) meets[mon] = true;
			}
			int count = 0;
			for(int i = 0; i < meets.length; i++){
				if(meets[i]) count++;
			}
			
			boolean isFinished = (count == this.meet);
			Map<Integer, Integer> feqMap = this.getFeq();
			if(isFinished){
				if(feqMap.containsKey(pickup)){
					feqMap.put(pickup, feqMap.get(pickup) + 1);
				} else{
					feqMap.put(pickup, 1);
				}
			}
			
			return isFinished;
		}
		
	}
	
	protected static class PriceExpectedValue implements RunResult{
		protected final String name;
		protected final MonsterPickupTrial monsterPickupTrial;
		protected final FeqPickupCriteria feqPickupCriteria;
		protected final double price;
		
		private double expectedValue;
		
		public PriceExpectedValue(String name, MonsterPickupTrial monsterPickupTrial, FeqPickupCriteria feqPickupCriteria, double price){
			this.name = name;
			this.monsterPickupTrial = monsterPickupTrial;
			this.feqPickupCriteria = feqPickupCriteria;
			this.price = price;
			
			this.expectedValue = 0;
		}
		
		@Override
		public void solve(){
			this.expectedValue = 0;
			
			this.monsterPickupTrial.trial(this.feqPickupCriteria, Assignment02.NUM_TRIAL);
			
			double expectedValue = 0;
			for(Entry<Integer, Integer> entry: this.feqPickupCriteria.getFeq().entrySet()){
				//System.out.println(entry.getKey() + " " + entry.getValue());
				expectedValue += ((this.price * entry.getKey()) * ((double)entry.getValue()/Assignment02.NUM_TRIAL));
			}
			
			this.expectedValue = expectedValue;
			
			System.out.println(this.name + " was finished.");
		}
		
		@Override
		public double getExpectedValue(){
			return this.expectedValue;
		}
		
	}
	
	private RunResult expectedValue;
	
	private Assignment04(RunResult expectedValue){
		this.expectedValue = expectedValue;
	}
	
	protected RunResult getRunResult(){
		return this.expectedValue;
	}
	
	@Override
	public void run(){
		this.expectedValue.solve();
	}

	public static void main(String[] args) throws FileNotFoundException{
		MonsterPickupTrial case01MonsterPickupTrial = new MonsterPickupTrial(Assignment02.TOTAL_MONSTER, Assignment02.NEW_MONSTER, 5);
		MonsterPickupTrial case02MonsterPickupTrial = new MonsterPickupTrial(Assignment02.TOTAL_MONSTER, Assignment02.NEW_MONSTER);
		MonsterPickupTrial case03MonsterPickupTrial = case02MonsterPickupTrial;
		
		double pickupPrice01 = 10;
		double pickupPrice02 = 5;
		double pickupPrice03 = pickupPrice01;
		
		FeqPickupCriteria case01Criteria = new CaseMeetCriteria(Assignment02.NEW_MONSTER);
		FeqPickupCriteria case02Criteria = new CaseMeetCriteria(Assignment02.NEW_MONSTER);
		FeqPickupCriteria case03Criteria = new CaseMeetWithExtraPickupCriteria(Assignment02.NEW_MONSTER, Assignment02.NEW_MONSTER, 10, new MonsterPickupTrial(Assignment02.TOTAL_MONSTER, Assignment02.NEW_MONSTER, 1, 0));
		
		Assignment04 case01 = new Assignment04(new PriceExpectedValue("Case a", case01MonsterPickupTrial, case01Criteria, pickupPrice01));
		Assignment04 case02 = new Assignment04(new PriceExpectedValue("Case b", case02MonsterPickupTrial, case02Criteria, pickupPrice02));
		Assignment04 case03 = new Assignment04(new PriceExpectedValue("Case c", case03MonsterPickupTrial, case03Criteria, pickupPrice03));
		
		Thread thread01 = new Thread(case01);
		Thread thread02 = new Thread(case02);
		Thread thread03 = new Thread(case03);
		
		System.out.println("Calculating...");
		
		thread01.start();
		thread02.start();
		thread03.start();
		try{
			thread01.join();
			thread02.join();
			thread03.join();
		} catch(InterruptedException e){
			e.printStackTrace();
			System.exit(-1);
		}
		
		PrintWriter writer = new PrintWriter(new File(Assignment04.OUTPUT_FILE));
		writer.println("case a," + case01.getRunResult().getExpectedValue());
		writer.println("case b," + case02.getRunResult().getExpectedValue());
		writer.println("case c," + case03.getRunResult().getExpectedValue());
		writer.close();
		
		System.out.println("Output file was created.");
	}
}
