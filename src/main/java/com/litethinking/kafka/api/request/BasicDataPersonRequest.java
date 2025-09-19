package com.litethinking.kafka.api.request;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BasicDataPersonRequest {

	public static class Address {
		private String streetAddress;
		private String country;
		private Location location;

		public String getCountry() {
			return country;
		}

		public Location getLocation() {
			return location;
		}

		public String getStreetAddress() {
			return streetAddress;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public void setStreetAddress(String streetAddress) {
			this.streetAddress = streetAddress;
		}

		@Override
		public String toString() {
			return "Address [streetAddress=" + streetAddress + ", country=" + country + ", location=" + location + "]";
		}

	}

	public static class Location {
		private double latitude;
		private double longitude;

		public double getLatitude() {
			return latitude;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}

		@Override
		public String toString() {
			return "Location [latitude=" + latitude + ", longitude=" + longitude + "]";
		}
	}

	public static class Passport {
		private String number;

		@JsonFormat(pattern = "yyyy-MM-dd")
		private LocalDate expirationDate;

		public LocalDate getExpirationDate() {
			return expirationDate;
		}

		public String getNumber() {
			return number;
		}

		public void setExpirationDate(LocalDate expirationDate) {
			this.expirationDate = expirationDate;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		@Override
		public String toString() {
			return "Passport [number=" + number + ", expirationDate=" + expirationDate + "]";
		}
	}

	private String firstName;
	private String lastName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	private Map<String, String> contacts;
	private Passport passport;
	private List<Address> addresses;

	public List<Address> getAddresses() {
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

	public Passport getPassport() {
		return passport;
	}

	public void setAddresses(List<Address> addresses) {
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

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	@Override
	public String toString() {
		return "BasicDataPersonRequest [firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", contacts=" + contacts + ", passport=" + passport + ", addresses=" + addresses + "]";
	}

}
