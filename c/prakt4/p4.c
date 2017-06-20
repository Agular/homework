#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define MATH_PI 3.14



// Calculates the area of the circle with a given diameter.
// NB! The diameter is given in mm and output is given in cm2!!
// This function 
float surface (int diameter){
    float radius = (float)diameter/20.0f;
    return MATH_PI * radius * radius;
}

int main(void){
    
    int diameter;
    
    printf("Please enter the radius of the circle(mm): ");
    scanf("%d", &diameter);
    float area = surface(diameter);
    
    printf("The area of the circle in user-simple calculations\n");
    printf("###################################################\n");
    printf("Surface area in cm²: %.4f\n\n", area);
    
    return 0;
}
