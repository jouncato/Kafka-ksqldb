package com.litethinking.kafka.broker.message;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BasicDataPassportMessage {

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
		return "BasicDataPassportMessage [number=" + number + ", expirationDate=" + expirationDate + "]";
	}

}
