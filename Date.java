/* Java class that represents a date consisting of a year, month, and day.
 * Methods include: setDate(), addDays(), addWeeks(), longDate(), dateValid(), daysTo()
 */

package codingAssignment8b;

// FIELDS -- declaration
public class Date {
	private int month;
	private int day;
	private int year;

	// CONSTRUCTOR -- default initialization date (calls setDate)
	public Date() {
		this.setDate(1, 1, 1970);
	}

	// CONSTRUCTOR -- date given from client (calls setDate)
	public Date(int month, int day, int year) {
		this.setDate(month, day, year);
	}

	// CONSTRUCTOR
	public void setDate(int month, int day, int year) {
		dateValid(month, day, year);

		this.month = month;
		this.day = day;
		this.year = year;
	}

	// METHOD -- mutator -- change month
	public void setMonth(int month) {
		dateValid(month, day, year);
		this.month = month;
	}

	// METHOD -- mutator -- change day
	public void setDay(int day) {
		dateValid(month, day, year);
		this.day = day;
	}

	// METHOD -- mutator -- change year
	public void setYear(int year) {
		dateValid(month, day, year);
		this.year = year;
	}

	// METHOD -- mutator -- adds or subtracts days passed in as parameter
	// algorithm inspired by sr2jr.com/textbook-solutions/computer-science/70902002/building-java-programs-a-back-to-basics-approach-classes
	public void addDays(int num) {

		// adding days
		while (num >= 1) {
			day++;
			num--;

			if ((month == 1 || month == 3 || month == 5 || month == 7 
					|| month == 8 || month == 10 || month == 12) && day == 32) {
				month++;
				day = 1;

				if (month == 13) {
					month = 1;
					year++;
				}
			}

			if ((month == 4 || month == 6 || month == 9
					|| month == 11) && day == 31) {
				month++;
				day = 1;
			}

			if (isLeapYear() && month == 2 && day == 30) {
				month++;
				day = 1;
			} else if (!isLeapYear() && month == 2 && day == 29) {
				month++;
				day = 1;
			}
		}

		// subtracts days
		while (num < 0) {
			day--;
			num++;

			if ((month == 1 || month == 3 || month == 5 || month == 7 
					|| month == 8 || month == 10 || month == 12) && day == 0) {
				month--;

				if (month != 2) {
					day = 30;
				} else if (isLeapYear() && month == 2) {
					day = 29;
				} else if (!isLeapYear() && month == 2) {
					day = 28;
				}

				if (month == 0) {
					year--;
					month = 12;
					day = 31;
				}
			}

			if ((month == 4 || month == 6 || month == 9
					|| month == 11) && day == 0) {
				month--;

				if (month != 2) {
					day = 31;
				}
			}

			if (month == 2 && day == 0) {
				month--;
				day = 31;
			}
		}
	}

	// METHOD -- mutator -- adds or subtracts weeks passed in as parameter
	public void addWeeks(int weeks) {
		addDays(weeks * 7);
	}

	//METHOD -- accessor -- returns month
	public int getMonth() {
		return month;
	}

	// METHOD -- accessor -- returns day
	public int getDay() {
		return day;
	}

	// METHOD -- accessor -- returns year
	public int getYear() {
		return year;
	}

	// METHOD -- accessor -- boolean check for leap year
	public boolean isLeapYear() {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return true;
		}
		return false;
	}

	// METHOD -- accessor -- displays date in long format (ex. August 13, 2001)
	public String longDate() {
		String[] months = {"January", "February", "March", "April", "May", "June", "July", 
				"August", "September", "October", "November", "December"};

		return months[month - 1] + " " + day + ", " + year;
	}
	
	// METHOD -- accessor -- difference between two dates in days
	public int daysTo(Date other) {
		Date x = new Date(this.getMonth(), this.getDay(), this.getYear());
		Date y = new Date(other.getMonth(), other.getDay(), other.getYear());
		
		int difference = 0;

		if (x.year < y.year || (x.year == y.year && x.month < y.month)
				|| (x.year == y.year && x.month == y.month && x.day < y.day)) {
			do {
				x.addDays(1);
				difference++;
			} while (x.month != y.month || x.day != y.day || x.year != y.year);
			return difference;
		} 
		
		if (y.year < x.year || (y.year == x.year && y.month < x.month)
				|| (y.year == x.year && y.month == x.month && y.day < x.day)) {
			do {
				y.addDays(1);
				difference++;
			} while (x.month != y.month || x.day != y.day || x.year != y.year);
			return difference * -1;
		} 
		return 0;
	}

	// METHOD -- accessor -- difference between two dates in days (static overload)
	public static int daysTo(Date x, Date y) {
		return x.daysTo(y);
	}

	// METHOD -- accessor -- prints date as a String w/ leading zeroes
	public String toString() {
		String[] array = new String[9];
		for (int i = 0; i < 9; i++) {
			array[i] = String.format("%02d", i + 1);
		}

		if (month < 10 && day < 10) {
			return array[month - 1] + "/" + array[day - 1] + "/" + year;
		} else if (month < 10) {
			return array[month - 1] + "/" + day + "/" + year;
		} else if (day < 10) {
			return month + "/" + array[day - 1] + "/" + year;
		}

		return month + "/" + day + "/" + year;
	}

	// METHOD -- accessor -- checks date validity
	public void dateValid(int month, int day, int year) {

		if (month < 1 || month > 12 || day < 1) {
			throw new IllegalArgumentException();
		}

		// sets limit of 31 for months with 31 days
		if ((month == 1 || month == 3 || month == 5 || month == 7 
				|| month == 8 || month == 10 || month == 12) && day > 31) {
			throw new IllegalArgumentException();
		}

		// sets limit of 30 days for months with 30 days
		if ((month == 4 || month == 6 || month == 9
				|| month == 11) && day > 30) {
			throw new IllegalArgumentException();
		}

		// checking days in February for leap years and non-leap years
		// my personal code taken from my own DayGrid.java
		if (((year % 4 == 0 && year % 100 != 0) 
				|| year % 400 == 0) && month == 2 && day > 29) {
			throw new IllegalArgumentException();
		} else if ((!(year % 4 == 0 && year % 100 != 0) 
				|| year % 400 == 0) && month == 2 && day > 28) {
			throw new IllegalArgumentException();
		}
	}
}
