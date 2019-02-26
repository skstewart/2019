/**
 * Name: Shayna Stewart
 * CS2560
 * Project 1
 * Due: 2/26/19
 */

#include <stdio.h>

int displaySeatingChart(void);
int pricing(void);
int menu(void);
int displaySeatingChart(void);
int buyTickets(void);
int viewTicketsSold(void);
int viewRevenue(void);
int viewAvailableInRow(void);
int viewTotalAvailable(void);
float revenue;
int seatsAvailable;
int ticketsSold;
float prices[15];
char seating[15][30]; //create table of seating




int main(void) {
    revenue = 0;
    ticketsSold = 0;
    seatsAvailable = 450;

	for(int i = 0; i < 15; i++){ //initialize all seats as available
		for(int j = 0; j < 30; j++){
			seating[i][j] = '#';
		}

	} //end double for loop

	pricing();
	displaySeatingChart();
	menu();




	return(0);
}

//Function: int pricing(void)
//Purpose: allows user to enter prices for each row
int pricing(void){
    for(int k = 0; k < 15; k++){ //set the prices for each row
    	printf("Please enter the price for seats in row %d:",k);
	    scanf("%f", &prices[k]);
	    }

	    return 0;

    }

int displaySeatingChart(void){
    static char seating[15][30]; //create table of seating
	for(int i = 0; i < 15; i++){ //initialize all seats as available
	    printf("Row %d: ",i);
		for(int j = 0; j < 30; j++){
			printf("%c",seating[i][j]);
		}
		printf("\n");
	}
		return 0;

}

int menu(void){
    int userChoice = 0;
    printf("Menu: \n1. View Seating Chart\n2. Buy tickets\n3. View tickets sold\n"
            "4. View total revenue\n5. View number of available seats in a row\n"
            "6. View number of seats available in auditorium\n7. Exit \n");
    scanf("%d",&userChoice);
    switch(userChoice){
        case 1: {displaySeatingChart();
            menu();
        }break;
        case 2: {buyTickets();
            menu();
        }break;
        case 3: {viewTicketsSold();
            menu();
        }break;
        case 4: {viewRevenue();
            menu();
        }break;
        case 5: {viewAvailableInRow();
            menu();
        }break;
        case 6: {viewTotalAvailable();
            menu();
        }break;
        case 7: {return 0;}

    }

    return 0;
}

int buyTickets(void){

    return 0;
}
int viewTicketsSold(void){

    return 0;
}
int viewRevenue(void){
    printf("Total revenue earned: $%f\n",revenue);
    return 0;
}
int viewAvailableInRow(void){
    int row = 0;
    int avail = 0;
    printf("Please enter a row number:");
    scanf("%d",&row);
    for(int x = 0; x < 30; x++){
        if(&seating[row][x] == '#')
            avail++;
    }
    printf("There are %d seats available in the row.\n",avail);
    return 0;
}
int viewTotalAvailable(void){
    printf("There are %d seats available in the auditorium.\n",seatsAvailable);
    return 0;
}


