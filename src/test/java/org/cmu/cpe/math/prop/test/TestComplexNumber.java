package org.cmu.cpe.math.prop.test;

import org.cmu.cpe.math.prop.number.Complex;
import org.junit.Test;

public class TestComplexNumber{
	@Test
	public void complexNumberTest(){
		Complex a = new Complex(0, 0.5*Math.PI);
		Complex b = new Complex(0, 2.0/3*Math.PI);
		Complex c = new Complex(1, 1);
		
		System.out.println(a.real + " + " + a.image + "i");
		System.out.println(a);
		System.out.println(a.exp());
		
		System.out.println(b.real + " + " + b.image + "i");
		System.out.println(b);
		System.out.println(b.exp());
		
		System.out.println(c.real + " + " + c.image + "i");
		System.out.println(c.add(1));
		System.out.println(c.mul(2));
		
	}
}
