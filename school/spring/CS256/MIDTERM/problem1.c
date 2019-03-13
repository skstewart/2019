/******************************************************************************
Name: Shayna Stewart
Class: CS2560
Description: Prints a multiplication table using the size entered by the user.

*******************************************************************************/

#include <stdio.h>

int main()
{
    int size = 0;
    printf("Please enter a column/row size: ");
    scanf("%d",&size);
    
    for(int i = 1; i < size+1; i++){ //double loop to assign 
        for(int j = 1; j < size+1; j++){
            printf("%d ",(i*j));
            
        }
        printf("\n");
        
    }

    return 0;
}
