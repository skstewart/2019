#include <iostream> 

using namespace std;

int remove(int*, int);

int main() {
	int n;
	cout << "Enter an integer greater than 1: ";
	cin >> n;
	int* N = new int[1024]; // allocating space
	int i, j;

	for (i = 2; i <= n; i++) { //creating our array of numbers beginning at 2 (1 is not prime)
		N[i - 2] = i;	
	
	}

	cout << "Entire array before removal of composite numbers:" << endl;
	for (i = 0; i < n-1; i++) {
		cout << N[i] << " ";	//print initial array
	}
	cout << endl;

	j = remove(&N[0], n);	//send to remove function

	cout << "Array after removal of composite numbers:" << endl;
	for (i = 0; i < j - 1; i++) {
		cout << *(N + i) << " ";
	}

	cout << endl;

	delete[] N;	//delete from memory 

	return 0;

}

int remove(int *N, int size) {
	int i, j;
	int toDelete;
	bool isComposite;

	for (i = 0; i < size; i++) {
		isComposite = false;
		for (j = 2; j < i+2; j++) { //if a number is divisible by anything but 1, it is composite.
			if (N[i] % j == 0){
				isComposite = true; //if composite, mark 
				toDelete = i; //mark 
			}
		}
		if (isComposite == true) { //if marked as composite,
			N[toDelete] = -1; //mark for removal
		}
	}
	j = 0;
	for (i = 0; i < size+1; i++) { //if marked, remove
		if (N[i] != -1) {
			N[j++] = N[i];
		}
	
	}
	return j;
}
