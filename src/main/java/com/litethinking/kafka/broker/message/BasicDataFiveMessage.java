package com.litethinking.kafka.broker.message;

import java.util.Map;
import java.util.UUID;

public class BasicDataFiveMessage {

	private Map<Integer, String> myMapAlpha;

	private Map<UUID, String> myMapBeta;

	public Map<Integer, String> getMyMapAlpha() {
		return myMapAlpha;
	}

	public Map<UUID, String> getMyMapBeta() {
		return myMapBeta;
	}

	public void setMyMapAlpha(Map<Integer, String> myMapAlpha) {
		this.myMapAlpha = myMapAlpha;
	}

	public void setMyMapBeta(Map<UUID, String> myMapBeta) {
		this.myMapBeta = myMapBeta;
	}

	@Override
	public String toString() {
		return "BasicDataFiveMessage [myMapAlpha=" + myMapAlpha + ", myMapBeta=" + myMapBeta + "]";
	}

}
