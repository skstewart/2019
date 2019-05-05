
#include <iostream>
#include "Date.h"

using namespace std;

void incrementOperator();
void decrementOperator();
void subtractionOperator();
void streamInsertionOperator();
void streamExtractionOperator();

Date date;
Date date1(4, 18, 2014);
Date date2(4, 10, 2014);

int userMonth;
int userDay;
int userYear;

int monthDays[12] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

string monthName[12] = { "January", "February", "March", "April", "May", "June", "July",
						 "August", "September", "October", "November", "December" };
enum months {
	January = 1, February = 2, March = 3, April = 4, May= 5, June = 6, July = 7,
	August= 8, September = 9, October = 10, November = 11, December = 12
};

int main() {
	streamExtractionOperator();

	cout << "\nThis is the date you entered:" << endl;
	date.print();
	cout << "\n";

	incrementOperator();
	date.print();
	streamInsertionOperator();

	decrementOperator();
	date.print();
	streamInsertionOperator();

	subtractionOperator();


	return 0;
}

void incrementOperator() {
	userDay++;
	date.setDay(userDay);

	if (userDay > monthDays[userMonth - 1] && userMonth < December) {
		if (userDay >= 30)
		{
			userMonth++;
			userDay = 1;
			date.setMonth(userMonth);
			date.setDay(userDay);
		}
		else if (userMonth == February)
		{
			userDay = 29;
			date.setDay(userDay);
		}
	}

	if (userMonth == December && userDay > 31)
	{
		userYear++;
		userMonth = 1;
		userDay = 1;
		date.setYear(userYear);
		date.setMonth(userMonth);
		date.setDay(userDay);
	}

	cout << "After using increment operator: " << endl;
}

void decrementOperator() {
	userDay--;
	date.setDay(userDay);

	if (userMonth == January && userDay == 1) {
		userMonth = December;
		userDay = 31;
		userYear--;
		date.setMonth(userMonth);
		date.setDay(userDay);
		date.setYear(userYear);
	}
	else if (userMonth == January && userDay > 1) {
		userDay--;
		date.setDay(userDay);
	}
	else if (userDay == 1) {
		userMonth--;
		date.setMonth(userMonth);

		if (monthDays[userMonth - 1] == 31) {
			userDay = 31;
			date.setDay(userDay);
		}
		else if (monthDays[userMonth - 1] == 30) {
			userDay = 30;
			date.setDay(userDay);
		}
		else if (userMonth == February) {
			userDay = 28;
			date.setDay(userDay);
		}
	}
	else if (userDay > 1) {
		userDay--;
		date.setDay(userDay);
	}

	cout << "After using decrement operator:" << endl;
}

void subtractionOperator() {
	cout << "After using subtraction operator: " << endl;
	cout << "The first date is: ";
	date1.print();
	cout << "The second date is: ";
	date2.print();

	int subtraction = date1.getDay() - date2.getDay();

	cout << "The number of days between these two days is: " << subtraction << endl;
	cout << "\n";
}

void streamInsertionOperator() {
	cout << "After using stream insertion operator:" << endl;

	cout << monthName[userMonth - 1] << " " << date.getDay() << ", " << date.getYear() << "\n" << endl;
}

void streamExtractionOperator() {
	cout << "Please enter a month number: ";
	cin >> userMonth;
	while (userMonth < 1 || userMonth > 12) {
		cout << "Please enter a real month number: ";
		cin >> userMonth;
	}

	cout << "Please enter a day: ";
	cin >> userDay;
	while (userDay < 1 || userDay > monthDays[userMonth - 1]) {
		cout << "Please enter a real month day: ";
		cin >> userDay;
	}

	cout << "Please enter a year: ";
	cin >> userYear;
	while (userYear < 1900 || userYear > 2020) {
		cout << "Please enter a year between 1900-2020: ";
		cin >> userYear;
	}

	date.setMonth(userMonth);
	date.setDay(userDay);
	date.setYear(userYear);
}
