package com.litethinking.kafka.broker.message;

public class BasicDataAddressMessage {

	private String streetAddress;
	private String country;
	private BasicDataLocationMessage location;

	public String getCountry() {
		return country;
	}

	public BasicDataLocationMessage getLocation() {
		return location;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setLocation(BasicDataLocationMessage location) {
		this.location = location;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	@Override
	public String toString() {
		return "BasicDataAddressMessage [streetAddress=" + streetAddress + ", country=" + country + ", location="
				+ location + "]";
	}

}
