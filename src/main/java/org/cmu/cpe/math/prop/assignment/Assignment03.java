package org.cmu.cpe.math.prop.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.cmu.cpe.math.prop.game.MonsterPickupTrial;

public class Assignment03{
	public static final int MAX_PICKUP = 50;
	public static final String OUTPUT_FILE = "assignment03-output.csv";
	
	public static void main(String[] args) throws FileNotFoundException{
		MonsterPickupTrial monsterPickupTrial = new MonsterPickupTrial(Assignment02.TOTAL_MONSTER, Assignment02.NEW_MONSTER);
		
		PrintWriter writer = new PrintWriter(new File(Assignment03.OUTPUT_FILE));
		
		for(int pickup = 1; pickup <= Assignment03.MAX_PICKUP; pickup++){
			double[] fn = monsterPickupTrial.trial(MonsterPickupTrial.getPickupLimitCriteria(pickup), Assignment02.NUM_TRIAL);
			
			double expectedValue = 0;
			for(int i = 0; i < fn.length; i++){
				expectedValue += (i * fn[i]);
			}
			
			writer.println(pickup + "," + expectedValue);
		}
		
		writer.close();
	}
	
}
