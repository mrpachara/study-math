package org.cmu.cpe.math.prop.assignment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.cmu.cpe.math.prop.fourier.DiscreteFourierTreansform;
import org.cmu.cpe.math.prop.number.Complex;
import org.cmu.cpe.math.prop.util.ArrayUtil;

public class Assignment01{
	public static final String INPUT_FILE = "input.txt";
	public static final String OUTPUT_FILE = "output.txt";
	
	public static void main(String[] args) throws IOException{
		List<Double> dataList = new ArrayList<>();
		
		Scanner scanner = new Scanner(new File(Assignment01.INPUT_FILE));
		
		while(scanner.hasNextDouble()) dataList.add(scanner.nextDouble());
		
		scanner.close();
		
		DiscreteFourierTreansform dft = new DiscreteFourierTreansform(ArrayUtil.toDoubleArray(dataList));
		Complex[] results = dft.getF();
		
		PrintWriter writer = new PrintWriter(new File(Assignment01.OUTPUT_FILE));
		
		NumberFormat formatter = new DecimalFormat("#0.00");
		
		for(Complex f: results){
			writer.println(f.format(formatter));
		}
		
		writer.close();
	}
	
}
