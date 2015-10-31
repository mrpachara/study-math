package org.cmu.cpe.math.prop.util;

import java.util.Collection;

public class ArrayUtil{
	public static double[] toDoubleArray(Collection<Double> collection){
		Double[] inputs = collection.toArray(new Double[]{});
		double[] results = new double[collection.size()];
		
		for(int i = 0; i < inputs.length; i++) results[i] = inputs[i].doubleValue();
		
		return results;
	}
}
