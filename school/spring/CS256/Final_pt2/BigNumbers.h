#ifndef BIGNUMBERS_BigNumbers_H_
#define BIGNUMBERS_BigNumbers_H_

#include <vector>
#include <iostream>
#include <map>

namespace BigNumbers
{
	class BigNumbers
	{
	private:
		
		bool positive;
		int base;
		unsigned int skip;
		static const int MAX_SIZE = 1000;

	public:
		//Constructors
		std::vector<int> number;
		BigNumbers();
		BigNumbers(long long);
		BigNumbers(std::string);
		BigNumbers(const BigNumbers& b);

		//Adding
		BigNumbers operator+(BigNumbers const &) const;
		BigNumbers &operator+=(BigNumbers const &);

		//Subtraction
		BigNumbers operator-(BigNumbers const &) const;
		BigNumbers &operator-=(BigNumbers const &);

		//Multiplication
		BigNumbers operator*(BigNumbers const &);
		BigNumbers &operator*=(BigNumbers const &);
		BigNumbers operator*(long long const &);
		BigNumbers &operator*=(int const &);


		//Access
		friend std::istream &operator>>(std::istream &, BigNumbers &);
		friend std::ostream &operator<<(std::ostream &, BigNumbers const &);

	private:
		int segment_length(int) const;

	};

}

#endif
