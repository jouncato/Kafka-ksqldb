package com.litethinking.kafka.api.request;

public class BasicDataThreeRequest {

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

	public static class DateTime {
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

	public static class DateTimeWithTimezone extends DateTime {
		private String timezone;

		public String getTimezone() {
			return timezone;
		}

		public void setTimezone(String timezone) {
			this.timezone = timezone;
		}

		@Override
		public String toString() {
			return "DateTimeWithTimezone [year=" + getYear() + ", month=" + getMonth() + ", date=" + getDate()
					+ ", hour=" + getHour() + ", minute=" + getMinute() + ", second=" + getSecond() + ", timezone="
					+ timezone + "]";
		}
	}

	private Date myDate;
	private Time myTime;
	private DateTime myDateTime;
	private DateTimeWithTimezone myDateTimeWithTimezone;

	public Date getMyDate() {
		return myDate;
	}

	public DateTime getMyDateTime() {
		return myDateTime;
	}

	public Time getMyTime() {
		return myTime;
	}

	public DateTimeWithTimezone getMyDateTimeWithTimezone() {
		return myDateTimeWithTimezone;
	}

	public void setMyDate(Date myDate) {
		this.myDate = myDate;
	}

	public void setMyDateTime(DateTime myDateTime) {
		this.myDateTime = myDateTime;
	}

	public void setMyTime(Time myTime) {
		this.myTime = myTime;
	}

	public void setMyDateTimeWithTimezone(DateTimeWithTimezone myDateTimeWithTimezone) {
		this.myDateTimeWithTimezone = myDateTimeWithTimezone;
	}

	@Override
	public String toString() {
		return "BasicDataThreeRequest [myDate=" + myDate + ", myTime=" + myTime + ", myDateTime=" + myDateTime
				+ ", myDateTimeWithTimezone=" + myDateTimeWithTimezone + "]";
	}

}
