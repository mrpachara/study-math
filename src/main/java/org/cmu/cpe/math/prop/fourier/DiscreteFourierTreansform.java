package org.cmu.cpe.math.prop.fourier;

import org.cmu.cpe.math.prop.number.Complex;

public class DiscreteFourierTreansform{
	private final Complex[] fs;

	public DiscreteFourierTreansform(double datas[]){
		int size = datas.length;
		Complex[] fs = new Complex[size];

		for(int k = 0; k < size; k++){
			Complex f = new Complex(0);
			for(int n = 0; n < size; n++){
				f = f.add(((new Complex(0, -(2*(Math.PI * n * k)/size))).exp()).mul(datas[n]));
			}
			
			fs[k] = f;
		}
		
		
		this.fs = fs;
	}

	public Complex[] getF(){
		return fs.clone();
	}
}
