/**
 * Name: Shayna Stewart
 * HW #3, Exercise 6-16
 * Due: 2/19/19
 */

#include <stdio.h>

double rate(int years, double BP, double DP, double P);
int input();


int main(void) {
	input();
	

	return(0);
}

int input(){
    double start = 0;
	double birthRate = -1;
	double deathRate = -1;
	int years = 0;
	
	//printf("Please input a starting size over 2: \n");
	//scanf("%lf",start);
	while(start < 2){
	    printf("Please input a starting size over 2: \n");
	    scanf("%lf",&start);
	}
	//printf("Please input a positive birth rate: \n");
	//scanf("%lf",birthRate);
	while(birthRate < 0){
	    printf("Please input a positive birth rate: \n");
	    scanf("%lf",&birthRate);
	}
	//printf("Please input a positive death rate: \n");
	//scanf("%lf",deathRate);
	while(deathRate < 0){
	    printf("Please input a positive death rate: \n");
	    scanf("%lf",&deathRate);
	}
	//printf("Please input a number of years greater than 1: \n");
	while(years < 1){
	    printf("Please input a number of years greater than 1: \n");
	    scanf("%d",&years);
	}
	
	rate(years,birthRate,deathRate,start);
    return 0;
}

double rate(int years, double BP, double DP, double P){
    double N = 0;
    if (years == 1){
         N = P + BP - DP;
        printf("Year %d population size: %lf\n",years,N);
        
    }
    else{
         N = P + BP - DP;
        printf("Year %d population size: %lf\n",years,N);
        return rate((years - 1),BP,DP,N);
        
    }
    
    return 0;
}
