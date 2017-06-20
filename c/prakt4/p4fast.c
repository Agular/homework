#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define FAST_PI 314

// Calculates the area of the circle with a given diameter.
// NB! The diameter is given in mm and output is given in cm2!!
void surfaceFast (int diameter){
    int area1 = FAST_PI * diameter * diameter / 40000;
    int area2 = FAST_PI * diameter * diameter / 4 % 10000;
    printf("%d.%04d\n",area1,area2);
}

int main(void){
    
    int diameter;
    
    printf("Please enter the radius of the circle(mm): ");
    scanf("%d", &diameter);
    
    printf("The area of the circle in fast calculations\n");
    printf("Surface area in cm²: ");
    surfaceFast(diameter);
    
    return 0;
}
