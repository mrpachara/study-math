package org.cmu.cpe.math.prop.number;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Complex extends Number{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8754811650502326964L;

	public final double real;
	public final double image;

	public Complex(double real, double image){
		this.real = real;
		this.image = image;
	}

	public Complex(double real){
		this(real, 0);
	}

	@Override
	public int intValue(){
		return (int)this.real;
	}

	@Override
	public long longValue(){
		return (long)this.real;
	}

	@Override
	public float floatValue(){
		return (float)this.real;
	}

	@Override
	public double doubleValue(){
		return this.real;
	}

	public double abs(){
		return Math.hypot(this.real, this.image);
	}

	public double phase(){
		return Math.atan2(this.real, this.image);
	}

	public Complex add(Complex c){
		return new Complex(this.real + c.real, this.image + c.image);
	}

	public Complex add(double r){
		return this.add(new Complex(r));
	}

	public Complex sub(Complex c){
		return new Complex(this.real - c.real, this.image - c.image);
	}

	public Complex sub(double r){
		return this.sub(new Complex(r));
	}

	public Complex mul(Complex c){
		/*
		 * (a + bi) * (c + di) = ac + adi + bic + bidi = (ac - bd) + (ad + bc)i
		 */
		return new Complex((this.real * c.real) - (this.image * c.image), (this.real * c.image) + (this.image * c.real));
	}

	public Complex mul(double r){
		return this.mul(new Complex(r));
	}

	public Complex exp(){
		return new Complex(Math.exp(this.real) * Math.cos(this.image), Math.exp(this.real) * Math.sin(this.image));
	}

	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(Double.toString(this.real)).append((this.image >= 0)? " + " : " - ").append(Double.toString(this.image)).append("i");
		
		return stringBuilder.toString();
	}

	public String format(NumberFormat formatter){
		double zero = Double.valueOf(formatter.format((double)0));
		
		String real = formatter.format(this.real);
		String image = formatter.format(Math.abs(this.image));
		
		StringBuilder stringBuilder = new StringBuilder(real);
		
		if(zero != Double.valueOf(image)) stringBuilder.append((this.image >= 0)? " + " : " - ").append(image).append("i");
		
		return stringBuilder.toString();
	}

	public String format(String pattern){
		return this.format(new DecimalFormat(pattern));
	}
}
