package com.litethinking.kafka.broker.message;

public class BasicDataTwoMessage {

	private long myEpochDay;

	private long myMillisOfDay;

	private long myEpochMillis;

	public long getMyEpochDay() {
		return myEpochDay;
	}

	public long getMyEpochMillis() {
		return myEpochMillis;
	}

	public long getMyMillisOfDay() {
		return myMillisOfDay;
	}

	public void setMyEpochDay(long myEpochDay) {
		this.myEpochDay = myEpochDay;
	}

	public void setMyEpochMillis(long myEpochMillis) {
		this.myEpochMillis = myEpochMillis;
	}

	public void setMyMillisOfDay(long myMillisOfDay) {
		this.myMillisOfDay = myMillisOfDay;
	}

}
