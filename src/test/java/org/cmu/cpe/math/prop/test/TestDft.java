package org.cmu.cpe.math.prop.test;

import static org.junit.Assert.assertArrayEquals;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.cmu.cpe.math.prop.fourier.DiscreteFourierTreansform;
import org.cmu.cpe.math.prop.number.Complex;
import org.junit.Test;

public class TestDft{
	private static NumberFormat formatter;
	static{
		formatter = new DecimalFormat("#0.00");
	}

	@Test
	public void firstTest(){
		DiscreteFourierTreansform dft = new DiscreteFourierTreansform(new double[]{
				1, 2, 3, 4
		});
		
		System.out.println("Test 01 start");
		
		Complex[] results = dft.getF();
		String[] outputs = new String[results.length];
		for(int i = 0; i < results.length; i++){
			String output = results[i].format(formatter);
			System.out.println(output);
			outputs[i]= output;
		}
		
		assertArrayEquals(new String[] {
				"10.00", "-2.00 + 2.00i", "-2.00", "-2.00 - 2.00i"
		}, outputs);
	}

	@Test
	public void scondTest(){
		DiscreteFourierTreansform dft = new DiscreteFourierTreansform(new double[]{
				-10.2, 4.3, 5.1, -4.3, 1.0, 5.55
		});
		
		System.out.println("Test 02 start");
		
		Complex[] results = dft.getF();
		String[] outputs = new String[results.length];
		for(int i = 0; i < results.length; i++){
			String output = results[i].format(formatter);
			System.out.println(output);
			outputs[i]= output;
		}
		
		assertArrayEquals(new String[] {
				"1.45", "-4.02 - 2.47i", "-22.47 + 4.63i", "-9.65", "-22.47 - 4.63i", "-4.03 + 2.47i"
		}, outputs);
	}

	@Test
	public void thirdTest(){
		DiscreteFourierTreansform dft = new DiscreteFourierTreansform(new double[]{
				1.00, 0.62, -0.07, -0.87, -1.51, -1.81, -1.70, -1.24, -0.64, -0.15, 0.05, -0.10
		});
		
		System.out.println("Test 03 start");
		
		Complex[] results = dft.getF();
		String[] outputs = new String[results.length];
		for(int i = 0; i < results.length; i++){
			String output = results[i].format(formatter);
			System.out.println(output);
			outputs[i]= output;
		}
	}

	@Test
	public void fouthTest(){
		DiscreteFourierTreansform dft = new DiscreteFourierTreansform(new double[]{
				1, 2, 3, 4, 3, 2, 1
		});
		
		System.out.println("Test 04 start");
		
		Complex[] results = dft.getF();
		String[] outputs = new String[results.length];
		for(int i = 0; i < results.length; i++){
			String output = results[i].format(formatter);
			System.out.println(output);
			outputs[i]= output;
		}
	}
}
