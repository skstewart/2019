#pragma once

#ifndef DATE_H
#define DATE_H

#include<iostream>
#include<string>

using namespace std;

class Date {
private:
	int month;
	int day;
	int year;
	string monthName[12] = { "January", "February", "March", "April", "May", "June", "July",
							 "August", "September", "October", "November", "December" };

	int monthDays[12] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	enum months {
		January = 1, February, March, April, May, June, July,
		August, September, October, November, December
	};

public:
	
	Date() {
		month = 1;
		day = 1;
		year = 0;
	}

	Date(int m, int d, int y) {
		month = m;
		day = d;
		year = y;
	}
  
	int getMonth() {
		return month;
	}
  
	void setMonth(int m) {
		month = m;
	}

	int getDay() {
		return day;
	}

	void setDay(int d) {
		day = d;
	}
  
  	int getYear() {
		return year;
	}

	void setYear(int y) {
		year = y;
	}


	void print() {
		cout << month << " / " << day << " / " << year << endl;
		cout << monthName[month - 1] << " " << day << ", " << year << endl;
		cout << day << " " << monthName[month - 1] << " " << year << endl<<endl;
	}
};


#endif 
