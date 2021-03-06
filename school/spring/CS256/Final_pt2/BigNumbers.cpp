#include <string>
#include <sstream>
#include <map>
#include "BigNumbers.h"
using namespace std;

namespace BigNumbers
{

	//Constructors for the BigNumber objects
	BigNumbers::BigNumbers()
	{
		positive = true;
		base = BigNumbers::MAX_SIZE;
		skip = 0;
	}
	BigNumbers::BigNumbers(const BigNumbers &b)
		: number(b.number),
		positive(b.positive),
		base(b.base),
		skip(b.skip) { }

	BigNumbers::BigNumbers(long long value)	//pushing a long into the vector
	{
		base = BigNumbers::MAX_SIZE;
		skip = 0;
		if (value < 0) {
			positive = false;
			value *= -1;
		}
		else {
			positive = true;
		}

		while (value) {
			number.push_back((int)(value % base));
			value /= base;
		}
	}

	BigNumbers::BigNumbers(string stringInteger) //pushing the large number (given a string) into the vector
	{
		int size = stringInteger.length();

		base = BigNumbers::MAX_SIZE;
		skip = 0;
		positive = (stringInteger[0] != '-');

		while (true) {
			if (size <= 0) break;
			if (!positive && size <= 1) break;

			int length = 0;
			int num = 0;
			int prefix = 1;
			for (int i(size - 1); i >= 0 && i >= size - 9; --i) {
				if (stringInteger[i] < '0' || stringInteger[i] > '9') break;
				num += (stringInteger[i] - '0') * prefix;
				prefix *= 10;
				++length;
			}
			number.push_back(num);
			size -= length;
		}
	}

	//Addition
	BigNumbers BigNumbers::operator+(BigNumbers const &b) const
	{
		BigNumbers c = *this;
		c += b;

		return c;
	}

	BigNumbers &BigNumbers::operator+=(BigNumbers const &b)
	{
		if (!b.positive) {
			return *this -= b;
		}
		vector<int>::iterator
			it1 = number.begin();
		vector<int>::const_iterator
			it2 = b.number.begin();
		int sum = 0;
		while (it1 != number.end() || it2 != b.number.end()) {
			if (it1 != number.end()) {
				sum += *it1;
			}
			else {
				number.push_back(0);
				it1 = number.end() - 1;
			}
			if (it2 != b.number.end()) {
				sum += *it2;
				++it2;
			}
			*it1 = sum % base;
			++it1;
			sum /= base;
		}
		if (sum) number.push_back(1);

		return *this;
	}

	

	//Subtraction
	BigNumbers BigNumbers::operator-(BigNumbers const &b) const
	{
		BigNumbers c = *this;
		c -= b;

		return c;
	}

	BigNumbers &BigNumbers::operator-=(BigNumbers const &b)
	{
		vector<int>::iterator
			it1 = number.begin();
		vector<int>::const_iterator
			it2 = b.number.begin();
		int dif = 0;
		while (it1 != number.end() || it2 != b.number.end()) {
			if (it1 != number.end()) {
				dif += *it1;
				++it1;
			}
			if (it2 != b.number.end()) {
				dif -= *it2;
				++it2;
			}
			if (dif < 0) {
				*(it1 - 1) = dif + base;
				dif = -1;
			}
			else {
				*(it1 - 1) = dif % base;
				dif /= base;
			}
		}
		if (dif < 0) positive = false;

		if (number.size() > 1)
		{
			do
			{
				it1 = number.end() - 1;
				if (*it1 == 0) number.pop_back();
				else break;
			} while (number.size() > 1);
		}

		return *this;
	}

	//Multiplication
	BigNumbers BigNumbers::operator*(BigNumbers const &b)
	{
		if (b.number.size() == 1) return *this *= b.number[0];
		vector<int>::iterator it1;
		vector<int>::const_iterator it2;
		BigNumbers c;
		for (it1 = number.begin(); it1 != number.end(); ++it1) {
			for (it2 = b.number.begin(); it2 != b.number.end(); ++it2) {
				c.skip = (unsigned int)(it1 - number.begin()) + (it2 - b.number.begin()); //TODO
				c += (long long)(*it1) * (*it2);
			}
		}
		c.skip = 0;

		return c;
	}

	BigNumbers &BigNumbers::operator*=(BigNumbers const &b)
	{
		*this = *this * b;

		return *this;
	}

	//Division, using same logic as ultiplication but multiplying by the reciprocal of the second number
	BigNumbers BigNumbers::operator/(BigNumbers const &b)
	{
		if (b.number.size() == 1) return *this *= 1/(b.number[0]);
		vector<int>::iterator it1;
		vector<int>::const_iterator it2;
		BigNumbers c;
		for (it1 = number.begin(); it1 != number.end(); ++it1) {
			for (it2 = b.number.begin(); it2 != b.number.end(); ++it2) {
				c.skip = (unsigned int)(it1 - number.begin()) + (it2 - b.number.begin()); //TODO
				c += (long long)(*it1) *( 1/(*it2));
			}
		}
		c.skip = 0;

		return c;
	}

	BigNumbers &BigNumbers::operator/=(BigNumbers const &b)
	{
		*this = *this / b;

		return *this;
	}


	//out stream (<< overloaded operator to print the number in the proper order)

	std::ostream &operator<<(std::ostream &out, BigNumbers const &a)
	{
		if (!a.number.size()) return out << 0;
		int i = a.number.size() - 1;
		for (; i >= 0 && a.number[i] == 0; --i);

		if (i == -1) return out << 0;
		if (!a.positive) out << '-';

		std::vector<int>::const_reverse_iterator it = a.number.rbegin() + (a.number.size() - i - 1);

		out << *it++;
		for (; it != a.number.rend(); ++it) {
			for (int i(0), len = a.segment_length(*it); i < 9 - len; ++i) out << '0';
			if (*it) out << *it;
		}

		return out;
	}

	

	int BigNumbers::segment_length(int segment) const
	{
		int length = 0;
		while (segment) {
			segment /= 10;
			++length;
		}

		return length;
	}

	
}
