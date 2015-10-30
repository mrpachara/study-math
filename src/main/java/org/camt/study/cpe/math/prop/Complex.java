package org.camt.study.cpe.math.prop;

public class Complex extends Number{
	private double real;
	private double image;

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

	public double real(){
		return this.real;
	}

	public double image(){
		return this.image;
	}

	public double abs(){
		return Math.hypot(this.real(), this.image());
	}

	public double phase(){
		return Math.atan2(this.real(), this.image());
	}
}
