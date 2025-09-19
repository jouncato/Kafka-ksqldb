package com.litethinking.kafka.broker.message;

import java.math.BigDecimal;

public class BasicDataOneMessage {

	private boolean myBoolean;

	private String myString;

	private String myAnotherString;

	private int myInteger;

	private long myLong;

	private float myFloat;

	private double myDouble;

	private BigDecimal myBigDecimal;

	public String getMyAnotherString() {
		return myAnotherString;
	}

	public BigDecimal getMyBigDecimal() {
		return myBigDecimal;
	}

	public double getMyDouble() {
		return myDouble;
	}

	public float getMyFloat() {
		return myFloat;
	}

	public int getMyInteger() {
		return myInteger;
	}

	public long getMyLong() {
		return myLong;
	}

	public String getMyString() {
		return myString;
	}

	public boolean isMyBoolean() {
		return myBoolean;
	}

	public void setMyAnotherString(String myAnotherString) {
		this.myAnotherString = myAnotherString;
	}

	public void setMyBigDecimal(BigDecimal myBigDecimal) {
		this.myBigDecimal = myBigDecimal;
	}

	public void setMyBoolean(boolean myBoolean) {
		this.myBoolean = myBoolean;
	}

	public void setMyDouble(double myDouble) {
		this.myDouble = myDouble;
	}

	public void setMyFloat(float myFloat) {
		this.myFloat = myFloat;
	}

	public void setMyInteger(int myInteger) {
		this.myInteger = myInteger;
	}

	public void setMyLong(long myLong) {
		this.myLong = myLong;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

}
