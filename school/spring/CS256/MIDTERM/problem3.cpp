#include <iostream>

class CreateArray {
	int findMin(float *arr, int n);
	int findMax(float *arr, int n);

	int main() {
		float * arr = NULL;
		int userChoice = -1;
		int size = -1;

		std::cout << "Please enter an array size:";
		std::cin >> size;
		arr = new float[size];

		std::cout << "Menu: \n1. Store a number in the array\n2. Retrieve the value of an element of the array.";
		std::cout << "\n3. Retrieve the highest value in the array.\n4. Retrieve the lowest value in the array.\n";
		std::cin >> userChoice;

		switch (userChoice) {
		case 1: { int n, value;
			std::cout << "Please enter an element number";
			std::cin >> n;
			std::cout << "Please enter a value to store";
			std::cin >> value;
			arr[n] = value;

		}break;
		case 2: {int n, value;
			std::cout << "Please enter an element number";
			std::cin >> n;
			std::cout << arr[n];
		case 3: {
			std::cout << "The highest value is: " << findMax(arr, size) << "";

		}break;
		case 4: {
			int min = findMin(arr, size);
		}break;
		}

				delete arr;
		}

		int findMin(float *arr, int n) {
			int min = -1;

			for (int x = 0; x < n; x++) {
				if (&arr[x] < min) {
					min = &arr[x];
				}


			} //end loop
			return min;
		}

		int findMax(float *arr, int n) {
			int max = -1;
			for (int x = 0; x < n; x++) {

				if (&arr[x] > max) {
					max = &arr[x];
				}

			} //end loop
			return max;
		}

	}
