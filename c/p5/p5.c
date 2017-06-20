#include <stdio.h>
#include <stdlib.h>
#include <math.h>

														double userSin (double x){
	double value = x;
	double summand = x;
	double i = 2.0;
	while (fabs(summand)>0.00005){
		summand *= -x*x/(i*(i+1.0));
		value += summand;
		i += 2.0;	
	}
	return value;
}

int main(){
	double x;
	printf("Enter the variable x for sin(x): ");
	scanf("%lf",&x);
	printf("The system function value of sin(%lf): %.4lf \n",x,sin(x));	
	printf("The user function value of sin(%lf): %.4lf \n",x,userSin(x));


	return 0;
}
