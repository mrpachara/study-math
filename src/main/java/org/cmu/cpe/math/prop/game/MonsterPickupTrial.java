package org.cmu.cpe.math.prop.game;

import java.util.Random;

public class MonsterPickupTrial{
	public static Random random = new Random(System.currentTimeMillis());

	public static final MonsterPickupCriteria getPickupLimitCriteria(int limit){
		return new MonsterPickupCriteria(){
			
			@Override
			public boolean finished(int pickup, boolean[] meets, int[] feqs){
				return (pickup == limit);
			}
		};
	}
	
	private int totalMon;
	private int newMon;
	private int newMonChance;
	private int oldMonChance;
	
	private int newMonRange;
	private int totalMonRange;
	
	public MonsterPickupTrial(int totalMon, int newMon, int newMonChance, int oldMonChance){
		this.totalMon = totalMon;
		this.newMon = newMon;
		this.newMonChance = newMonChance;
		this.oldMonChance = oldMonChance;

		this.newMonRange = this.newMon * this.newMonChance;
		this.totalMonRange = ((this.totalMon - this.newMon) * this.oldMonChance) + this.newMonRange;
	}
	
	
	public MonsterPickupTrial(int totalMon, int newMon, int newMonChance){
		this(totalMon, newMon, newMonChance, 1);
	}
	
	public MonsterPickupTrial(int totalMon, int newMon){
		this(totalMon, newMon, 1, 1);
	}
	
	public int pickup(){
		int mon = random.nextInt(this.totalMonRange);
		
		return (mon / this.newMonChance);
	}
/*	
	public double[] trial(int numPickup, int numTrial){
		return this.trial(MonsterPickupTrial.getPickupLimitCriteria(numPickup), numTrial);
	}
*/	
	public double[] trial(MonsterPickupCriteria criteria, int numTrial){
		int[] feqs = new int[this.newMon + 1];
		for(int i = 0; i < feqs.length; i++) feqs[i] = 0;
		
		for(int trial = 0; trial < numTrial; trial++){
			boolean[] meets = new boolean[this.newMon];
			for(int i = 0; i < meets.length; i++) meets[i] = false;
			
			for(int pickup = 0; !criteria.finished(pickup, meets, feqs); pickup++){
				int mon = this.pickup();
				
				if(mon < this.newMon) meets[mon] = true;
			}
			
			int count = 0;
			for(int i = 0; i < meets.length; i++){
				if(meets[i]) count++;
			}
			
			feqs[count]++;
		}
		
		double[] props = new double[feqs.length];
		for(int i = 0; i < props.length; i++) props[i] = (double)feqs[i]/numTrial;
		
		return props;
	}
}
