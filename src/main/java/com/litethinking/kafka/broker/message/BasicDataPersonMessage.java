package com.litethinking.kafka.broker.message;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BasicDataPersonMessage {

	private String firstName;
	private String lastName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	private Map<String, String> contacts;
	private BasicDataPassportMessage passport;
	private List<BasicDataAddressMessage> addresses;

	public List<BasicDataAddressMessage> getAddresses() {
		return addresses;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Map<String, String> getContacts() {
		return contacts;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public BasicDataPassportMessage getPassport() {
		return passport;
	}

	public void setAddresses(List<BasicDataAddressMessage> addresses) {
		this.addresses = addresses;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setContacts(Map<String, String> contacts) {
		this.contacts = contacts;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassport(BasicDataPassportMessage passport) {
		this.passport = passport;
	}

	@Override
	public String toString() {
		return "BasicDataPersonMessage [firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", contacts=" + contacts + ", passport=" + passport + ", addresses=" + addresses + "]";
	}

}
