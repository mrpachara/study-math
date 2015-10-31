package org.cmu.cpe.math.prop.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.cmu.cpe.math.prop.game.MonsterPickupTrial;

public class Assignment02{
	public static final int TOTAL_MONSTER = 100;
	public static final int NEW_MONSTER = 5;
	public static final int NUM_PICKUP = 20;
	public static final int NUM_TRIAL = 1000000;
	
	public static final String OUTPUT_FILE = "assignment02-output.csv";
	
	public static void main(String[] args) throws FileNotFoundException{
		MonsterPickupTrial monsterPickupTrial = new MonsterPickupTrial(Assignment02.TOTAL_MONSTER, Assignment02.NEW_MONSTER);
		
		double[] fn = monsterPickupTrial.trial(MonsterPickupTrial.getPickupLimitCriteria(Assignment02.NUM_PICKUP), Assignment02.NUM_TRIAL);
		
		PrintWriter writer = new PrintWriter(new File(Assignment02.OUTPUT_FILE));
		
		double expectedValue = 0;
		for(int i = 0; i < fn.length; i++){
			writer.println(i + "," + fn[i]);
			expectedValue += (i * fn[i]);
		}
		
		writer.println(",");
		writer.println("E(X)=," + expectedValue);
		
		writer.close();
	}
	
}
