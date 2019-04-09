#include <iostream> 

#include <vector> 

#include <string> 

using namespace std;

int remove_if(int*, int);

int main() {
	int n;
	std::cout << "Enter an integer greater than 1: ";
	std::cin >> n;
	int* N = (int*)malloc((n - 1) * sizeof(int));
	int i, j;

	for (i = 2; i <= n; i++) {
		N[i = 2] = i;
	
	}

	cout << "Array before removal of composite numbers:" << endl;
	for (i = 0; i < n; i++) {
		cout << N[i] << endl;
	}

	j = remove_if(&N[0], n);
	cout << "Array after removal of composite numbers:" << endl;
	for (i = 0; i < j - 1; i++) {
		cout << *(N + i) << endl;
	}

	cout << endl;

}

int remove_if(int *N, int size) {
	int i, j;
	int flag, comp_i;

	for (i = 0; i < size; i++) {
		flag = 0;
		for (j = 2; j < i; j++) {
			if (N[i] % j == 0){
				flag = 1;
				comp_i = i;
			}
		}
		if (flag == 1) {
			N[comp_i] = -1;
		}
	}
	j = 0;
	for (i = 0; i < size; i++) {
		if (N[i] != -1) {
			N[j++] = N[i];
		}
	
	}
	N = (int*)realloc(N, sizeof(int)*j);
	return j;
}
