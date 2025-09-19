package com.litethinking.kafka.api.request;

public class BasicDataFiveRequest {

	private int alphaElementsCount;
	private int betaElementsCount;

	public int getAlphaElementsCount() {
		return alphaElementsCount;
	}

	public int getBetaElementsCount() {
		return betaElementsCount;
	}

	public void setAlphaElementsCount(int alphaElementsCount) {
		this.alphaElementsCount = alphaElementsCount;
	}

	public void setBetaElementsCount(int betaElementsCount) {
		this.betaElementsCount = betaElementsCount;
	}

	@Override
	public String toString() {
		return "BasicDataFiveRequest [alphaElementsCount=" + alphaElementsCount + ", betaElementsCount="
				+ betaElementsCount + "]";
	}

}
