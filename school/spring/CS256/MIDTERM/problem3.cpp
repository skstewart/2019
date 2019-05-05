
#include <iostream>
using namespace std;



	int findMin(float arr[], int n) {
		int min = arr[0];

		for (int x = 0; x < n; x++) {
			if (arr[x] < min) {
				min = arr[x];
			}


		} //end loop
		return min;
	}

	int findMax(float arr[], int n) {
		int max = -1;
		for (int x = 0; x < n; x++) {

			if (arr[x] > max) {
				max = arr[x];
			}

		} //end loop
		return max;
	}

	int main() {
		float * arr = NULL;
		int userChoice = -1;
		int size = -1;

		cout << "Please enter an array size:" << endl;
		cin >> size;
		arr = new float[size];
		for (int i = 0; i < size; i++)
			arr[i] = 0;

	
		while (true) {
			cout << "Menu: \n1. Store a number in the array\n2. Retrieve the value of an element of the array.";
			cout << "\n3. Retrieve the highest value in the array.\n4. Retrieve the lowest value in the array.\n5. Exit" << endl;
			cin >> userChoice;
			cout << endl;

			switch (userChoice) {
			case 1: { int n, value;
				cout << "Please enter an element number: ";
				cin >> n;
				cout << "Please enter a value to store: ";
				cin >> value;
				arr[n] = value;

			}break;
			case 2: {int n, value;
				cout << "Please enter an element number: ";
				cin >> n;
				cout << arr[n];
			case 3: {
				cout << "The highest value is: " << findMax(arr, size) << endl;

			}break;
			case 4: {
				int min = findMin(arr, size);
				cout << "The lowest value is: " << min << endl;
			}break;
			case 5: {exit(0); }
			}

					delete arr;
			}
		}
	}
