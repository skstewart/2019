#include <iostream>
using namespace std;

template <typename T>
inline T const& Max(T const& firstData, T const& secondData)
{
	return firstData < secondData ? secondData : firstData;
}
template <typename T>
inline T const& Min(T const& firstData, T const& secondData)
{
	return firstData > secondData ? secondData : firstData;
}

int main()
{
	cout << "Max(1, 24) is: " << Max(1, 24) << endl;
	cout << "Max(-26, -12) is: " << Max(-26, -12) << endl;
	cout << "Max(123.456, 789.123) is: " << Max(123.456, 789.123) << endl;
	cout << "Max(1234, 1111) is: " << Max(1234, 1111) << endl;
	cout << "Max(shayna, Shayna) is: " << Max("shayna","Shayna") << endl;


	cout << "Min(1, 24) is: " << Min(1, 24) << endl;
	cout << "Min(-26, -12) is: " << Min(-26, -12) << endl;
	cout << "Min(123.456, 789.123) is: " << Min(123.456, 789.123) << endl;
	cout << "Min(1234, 1111) is: " << Min(1234, 1111) << endl;
	cout << "Min(shayna, Shayna) is: " << Min("shayna", "Shayna") << endl;
}
