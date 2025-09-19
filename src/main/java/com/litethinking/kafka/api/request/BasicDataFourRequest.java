package com.litethinking.kafka.api.request;

public class BasicDataFourRequest {

	private int arrayElementsCount;
	private int listElementsCount;
	private int setElementsCount;

	public int getArrayElementsCount() {
		return arrayElementsCount;
	}

	public int getListElementsCount() {
		return listElementsCount;
	}

	public int getSetElementsCount() {
		return setElementsCount;
	}

	public void setArrayElementsCount(int arrayElementsCount) {
		this.arrayElementsCount = arrayElementsCount;
	}

	public void setListElementsCount(int listElementsCount) {
		this.listElementsCount = listElementsCount;
	}

	public void setSetElementsCount(int setElementsCount) {
		this.setElementsCount = setElementsCount;
	}

}
