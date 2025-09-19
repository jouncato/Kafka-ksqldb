package com.litethinking.kafka.api.request;

public class BasicDataTwoRequest {

	public static class Date {
		private int year;
		private int month;
		private int date;

		public int getDate() {
			return date;
		}

		public int getMonth() {
			return month;
		}

		public int getYear() {
			return year;
		}

		public void setDate(int date) {
			this.date = date;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public void setYear(int year) {
			this.year = year;
		}

		@Override
		public String toString() {
			return "Date [year=" + year + ", month=" + month + ", date=" + date + "]";
		}
	}

	public static class Time {
		private int hour;
		private int minute;
		private int second;

		public int getHour() {
			return hour;
		}

		public int getMinute() {
			return minute;
		}

		public int getSecond() {
			return second;
		}

		public void setHour(int hour) {
			this.hour = hour;
		}

		public void setMinute(int minute) {
			this.minute = minute;
		}

		public void setSecond(int second) {
			this.second = second;
		}

		@Override
		public String toString() {
			return "Time [hour=" + hour + ", minute=" + minute + ", second=" + second + "]";
		}
	}

	public static class Timestamp {
		private int year;
		private int month;
		private int date;
		private int hour;
		private int minute;
		private int second;

		public int getDate() {
			return date;
		}

		public int getHour() {
			return hour;
		}

		public int getMinute() {
			return minute;
		}

		public int getMonth() {
			return month;
		}

		public int getSecond() {
			return second;
		}

		public int getYear() {
			return year;
		}

		public void setDate(int date) {
			this.date = date;
		}

		public void setHour(int hour) {
			this.hour = hour;
		}

		public void setMinute(int minute) {
			this.minute = minute;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public void setSecond(int second) {
			this.second = second;
		}

		public void setYear(int year) {
			this.year = year;
		}

		@Override
		public String toString() {
			return "Timestamp [year=" + year + ", month=" + month + ", date=" + date + ", hour=" + hour + ", minute="
					+ minute + ", second=" + second + "]";
		}
	}

	private Date myDate;
	private Time myTime;
	private Timestamp myTimestamp;

	public Date getMyDate() {
		return myDate;
	}

	public Time getMyTime() {
		return myTime;
	}

	public Timestamp getMyTimestamp() {
		return myTimestamp;
	}

	public void setMyDate(Date myDate) {
		this.myDate = myDate;
	}

	public void setMyTime(Time myTime) {
		this.myTime = myTime;
	}

	public void setMyTimestamp(Timestamp myTimestamp) {
		this.myTimestamp = myTimestamp;
	}

	@Override
	public String toString() {
		return "BasicDataTwoRequest [myDate=" + myDate + ", myTime=" + myTime + ", myTimestamp=" + myTimestamp + "]";
	}

}
