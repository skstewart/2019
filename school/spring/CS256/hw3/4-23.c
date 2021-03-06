/**
 * Name: Shayna Stewart
 * HW #3, Exercise 4-23
 * Due: 2/19/19
 */

#include <stdio.h>
int circle(void);
int rectangle(void);
int triangle(void);
int menu(void);

int main(void) {
	menu();
	return(0);
}

int menu(){
	int n = 0;
		printf("Menu:\n"
				"1. Calculate the Area of a Circle\n"
				"2. Calculate the Area of a Rectangle\n"
				"3. Calculate the Area of a Triangle\n"
				"4. Exit\n");
		scanf("%d",&n);
		switch(n){
		case 1:{
		    circle();
		    menu();

		}break;
		case 2: {
		    rectangle();
		    menu();

		}break;
		case 3: {
		    triangle();
		    menu();

		}break;
		
		case 4: {
			return(0);
		}
			break;
			
		
		default: menu();
			break;
		}
		
	return 0;
	
	
	
}

int circle(){
	double a = 0;
	double radius = 0;
	printf("Please enter a radius: ");
	scanf("%lf",&radius);
	a = radius*radius*3.14159;

	printf("Area of circle:       %lf \n", a);
	return 0;
}


int rectangle(){
	double a = 0;
	double length;
	double height;
	printf("Please enter a length: ");
	scanf("%lf",&length);
	printf("Please enter a height : ");
	scanf("%lf",&height);
	a = length*height;
	printf("Area of rectangle:       %lf \n", a);
	return 0;
}

int triangle(){
	double a = 0;
	double length;
	double height;
		printf("Please enter a length: ");
		scanf("%lf",&length);
		printf("Please enter a height : ");
		scanf("%lf",&height);
		a = (length*height)*.5;
		printf("Area of triangle:       %lf \n", a);
	return 0;
}
