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
		static const int MAX_SIZE = 1000; //max size, per project description, is n=1000 digits long

	public:
		//Constructors
		std::vector<int> number; //vector to hold the large number
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


		//Division
		BigNumbers operator/(BigNumbers const &);
		BigNumbers &operator/=(BigNumbers const &);



		//Access
		friend std::ostream &operator<<(std::ostream &, BigNumbers const &); //helper operator, allows cout<< stream to properly print the number

	private:
		int segment_length(int) const;

	};

}

#endif
